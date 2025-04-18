package nl.hannahschellekens.grill.algorithm

import nl.hannahschellekens.grill.algorithm.HungarianAlgorithm.Goal
import nl.hannahschellekens.grill.graph.AdjacencyList
import nl.hannahschellekens.grill.graph.Matching
import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.operations.*
import nl.hannahschellekens.grill.util.DEFAULT_EPSILON
import nl.hannahschellekens.grill.util.approxZero
import nl.hannahschellekens.grill.util.toOrdered
import kotlin.math.min

/**
 * Solves a job assignment problem represented by a square (`n`x`n`) cost matrix.
 *
 * Assigns rows `i` to columns `j` as to where the total cost, sum of all `(i,j)`, is optimised for a given
 * [Goal]: maximising or minimising the total cost.
 *
 * The algorithm is executed upon calling [assignJobs] and returns a mapping `i -> j` which maps the row indices
 * to the column indices of the assignment.
 *
 * I do not want to claim anything about runtime yet. It's too scary.
 * With a _correct_ implementation it should be _at most_ `O(n^4)` with `n` being the amount of workers.
 * From experimental and a quick handwavy analysis I arrived at an approximate run time of `O(n^3)` for `n` workers.
 *
 * @param costMatrix The `n`x`n` cost matrix of assigning worker `i` at the rows to the job `j` on the columns.
 * Each matrix element `(i,j)` is the cost of assigning worker `i` to job `j`.
 * @param goal How to optimize the assignment.
 * @param epsilon Floating point equality threshold.
 * @param maxIterations The maximum amount of iterations the algorithm may use.
 *
 * @author Hannah Schellekens
 */
open class HungarianAlgorithm(
    val costMatrix: Matrix<Double>,
    val goal: Goal = Goal.MINIMIZE,
    val epsilon: Double = DEFAULT_EPSILON,
    val maxIterations: Int = Int.MAX_VALUE
) : JobAssignmentAlgorithm {

    /**
     * Working matrix used by the algorithm.
     */
    private lateinit var matrix: MutableMatrix<Double>

    init {
        check(costMatrix.isSquare) { "Cost matrix must be square, got <${costMatrix.dimension}> " }
    }

    override fun assignJobs(): JobAssignment {
        if (costMatrix.isEmpty()) return emptyMap()

        // Make a working copy.
        matrix = costMatrix.toMatrix()

        // Prepare matrix.
        if (goal == Goal.MAXIMIZE) {
            maximize()
        }
        matrix.subtractRowMinima()
        matrix.subtractColumnMinima()

        var iterationCount = 0
        while (iterationCount < maxIterations) {
            // Represent the matrix as a bipartite graph:
            // - Partition A containing vertices for each worker.
            // - Partition B containing vertices for each job.
            // Every edge in the graph represents a 0 in the matrix.
            // A job assignment requires each worker to be assigned a job with a 0 in the matrix, i.e. an edge in the
            // graph. A maximum bipartite matching covering all workers means we have found our assignment!
            val graph = buildCoverGraph()
            val matching = graph.maximumBipartiteMatching(matrix.height)

            // We have found a matching for all workers!
            if (matching.size == matrix.height) {
                return matching.toJobAssignment()
            }
            // Not done yet: create additional zeros and try again!
            else {
                val (rowCover, columnCover) = matching.toLineCovers(graph)
                createAdditionalZeros(rowCover, columnCover)
            }
            iterationCount++
        }
        error("Maximum iterations <$iterationCount> exceeded (<$maxIterations>)")
    }

    /**
     * Updates the matrix to start finding an assignment to maximize the total cost.
     */
    private fun maximize() {
        // To maximize: negate all values and make the matrix non-negative.
        var smallest = Double.MAX_VALUE
        matrix.setAll {
            smallest = min(it, smallest)
            -it
        }

        matrix.setAll { it - smallest }
    }

    /**
     * Converts the [Matching] to the [JobAssignment] format.
     */
    private fun Matching.toJobAssignment(): JobAssignment = HashMap<Int, Int>().apply {
        for ((vertexA, vertexB) in this@toJobAssignment) {
            // Partition B is offset by `matrix.width`.
            if (vertexA < vertexB) {
                put(vertexA, vertexB - matrix.width)
            }
            else put(vertexB, vertexA - matrix.width)
        }
    }

    /**
     * Calculates which rows and which columns are covered by lines.
     *
     * @param coverGraph See [buildCoverGraph].
     * @return A pair of sets `({rowCovers},{columnCovers})` containing the indices of the rows and columns covered by
     * this matching.
     */
    private fun Matching.toLineCovers(coverGraph: AdjacencyList): Pair<Set<Int>, Set<Int>> {
        val matchedRows = this.map { it.first }.toSet()
        val unmatched = (0 until matrix.height).filter { it !in matchedRows }

        val totalVisited = HashSet<Int>()
        val visitedRows = HashSet<Int>()
        val visitedColumns = HashSet<Int>()

        // BFS: explore all zeroes.
        // All explored rows will be excluded from the cover.
        // All explored columns will be included into the cover.
        unmatched.forEach { start ->
            totalVisited += start
            visitedRows += start

            // Maps whether `Vertex arrived via a -> 0 (unmatched), 1 (matched)
            val stack = ArrayDeque<Pair<Int, Boolean> /* Vertex -> Arrived matched? */>()
            coverGraph.adjacentVertices(start).forEach { stack.addFirst(it to false) }

            while (stack.isNotEmpty()) {
                val (newVertex, arrivedMatched) = stack.removeFirst()
                if (newVertex >= matrix.height) visitedColumns.add(newVertex - matrix.height) else visitedRows.add(newVertex)
                totalVisited.add(newVertex)

                coverGraph.adjacentVertices(newVertex).forEach {
                    if (it !in totalVisited) {
                        val candidateEdge = newVertex toOrdered it
                        if (candidateEdge in this /* Matching */ != arrivedMatched) {
                            stack.addLast(it to arrivedMatched.not())
                        }
                    }
                }
            }
        }

        val rowCover = (0 until matrix.height).filter { it !in visitedRows }.toSet()
        return rowCover to visitedColumns
    }

    /**
     * Build an undirected bipartite graph with vertices `0..n-1` representing the rows in the first partition,
     * and vertices `n..2n-1` represesnting the columns in the second partition.
     * Every zero at location `(i,j)` is represented by an undirected edge `(i,n+j)`
     */
    private fun buildCoverGraph() = AdjacencyList(matrix.width + matrix.height).apply {
        matrix.forRowAndColumns { row, col ->
            val cost = matrix[row, col]
            if (cost.approxZero(epsilon)) {
                addUndirectedEdge(row, matrix.width + col)
            }
        }
    }

    /**
     * Takes all uncovered elements from the cost matrix and subtracts its minimum from all of them.
     * This minimum is then added to the elements that are covered by both the row cover and column cover.
     * Modifies [matrix].
     *
     * @param rowCover The indices of the rows that are covered.
     * @param columnCover The indices of the columns that are covered.
     */
    private fun createAdditionalZeros(rowCover: Set<Int>, columnCover: Set<Int>) {
        val smallestUncovered = matrix.minIf { row, col -> row !in rowCover && col !in columnCover }
            ?: error("There is no smallest cover value: rowCover <$rowCover> and/or columnCover <$columnCover> must be incorrect ")

        // Subtract this value from all uncovered values. Add double the value to doubly covered elements.
        matrix.forRowAndColumns { row, col ->
            if (row !in rowCover && col !in columnCover) {
                matrix[row, col] -= smallestUncovered
            }
            else if (row in rowCover && col in columnCover) {
                matrix[row, col] += smallestUncovered * 2
            }
        }
    }

    /**
     * Whether the algorithm must maximize or minimize the total cost.
     *
     * @author Hannah Schellekens
     */
    enum class Goal {

        /**
         * Maximize the total assignment cost.
         */
        MAXIMIZE,

        /**
         * Minimize the total assignment cost.
         */
        MINIMIZE
        ;
    }
}
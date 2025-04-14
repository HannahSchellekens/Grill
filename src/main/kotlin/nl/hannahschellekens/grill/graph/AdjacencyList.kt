package nl.hannahschellekens.grill.graph

import nl.hannahschellekens.grill.matrix.Matrix
import nl.hannahschellekens.grill.matrix.intMatrix
import java.util.*
import kotlin.collections.HashSet

/**
 * Creates a new graph with `initialSize` vertices represented by an adjacency list.
 *
 * @param initialVertexCount The initial amount of vertices in the graph.
 */
open class AdjacencyList(initialVertexCount: Int) {

    private val adjacencyList: MutableList<MutableList<Int>> = MutableList(initialVertexCount) {
        ArrayList()
    }

    /**
     * Root vertices of the graph. Empty if not used or relevant.
     */
    private val roots = HashSet<Int>()

    /**
     * The amount of vertices in the adjacency list.
     */
    var vertexCount: Int = initialVertexCount
        private set

    /**
     * The total amount of edges in the graph.
     * For undirected graphs the count will seem double (as there are 2 directed edges for each edge).
     */
    var edgeCount: Int = 0
        private set

    /**
     * Get all vertices adjacent to this vertex.
     *
     * @param vertex The index of the vertex to get the adjacent vertices of.
     */
    fun adjacentVertices(vertex: Int): List<Int> {
        return adjacencyList[vertex]
    }

    /**
     * Appends a new vertex to the graph.
     *
     * @param isRoot Whether to mark this vertex as a root node.
     *
     * @return The index of the added vertex.
     */
    fun addVertex(isRoot: Boolean = false): Int {
        vertexCount++
        adjacencyList.add(ArrayList())
        if (isRoot) {
            markAsRoot(vertexCount - 1)
        }
        return vertexCount - 1
    }

    /**
     * Adds a directed edge from vertex `from` to vertex `to`.
     * Does not check for duplicate edges.
     */
    fun addEdge(from: Int, to: Int) {
        adjacencyList[from].add(to)
        edgeCount++
    }

    /**
     * Adds an undirected edge between vertices `vertexA` and `vertexB`.
     * Does not check for duplicate edges.
     */
    fun addUndirectedEdge(vertexA: Int, vertexB: Int) {
        addEdge(vertexA, vertexB)
        addEdge(vertexB, vertexA)
    }

    /**
     * Checks whether vertex `from` has an edge directed to `to`.
     */
    fun isConnected(from: Int, to: Int): Boolean {
        return adjacencyList[from].contains(to)
    }

    /**
     * Calculates the degree of the given vertex, i.e. the amount of edges that end in this vertex.
     * For undirected graphs, use [degreeUndiredcted] instead.
     *
     * @param vertex The vertex to get the degree of.
     * @return The degree: amount of edges ending in this vertex.
     */
    fun degree(vertex: Int): Int = adjacencyList.count {
        it.contains(vertex)
    }

    /**
     * Calculates the degree of the given vertex, i.e. the amount of edges that are connected to it.
     * Assumes this adjacency list is one of an undirected graph.
     *
     * @param vertex The vertex to get the degree of.
     * @return The degree: amount of edges connected of the vertex.
     */
    fun degreeUndiredcted(vertex: Int): Int {
        // All edges are added twice.
        return adjacencyList[vertex].size
    }

    /**
     * Marks the given vertex to be a root node.
     */
    fun markAsRoot(vertex: Int) = roots.add(vertex)

    /**
     * Removes this vertex as a root.
     */
    fun removeRoot(vertex: Int): Boolean = roots.remove(vertex)

    /**
     * Returns all leaves of the graph starting from the roots. If there are no roots, there are no leaves.
     */
    fun findLeaves(): Set<Int> {
        if (roots.isEmpty()) return emptySet()

        val covered = HashSet<Int>()
        val leaves = HashSet<Int>()
        val queue = ArrayDeque<Int>()
        queue.addAll(roots)
        while (queue.isNotEmpty()) {
            val element = queue.removeFirst()
            covered.add(element)
            val adjacent = adjacentVertices(element) - covered
            if (adjacent.isEmpty()) {
                leaves.add(element)
            }
            else adjacent.forEach {
                queue.addLast(it)
            }
        }
        return leaves
    }

    /**
     * Turns the adjacency list to a matrix format with a `1` at `(i,j)` denoting that the graph contains edge `(i,j)`.
     * A `0` denotes that edge `(i,j)` is not part of the graph.
     */
    fun toMatrix(): Matrix<Int> {
        return intMatrix(vertexCount, vertexCount) { row, col ->
            if (isConnected(row, col)) 1 else 0
        }
    }

    override fun toString(): String = buildString {
        append("{")
        adjacencyList.forEachIndexed { index, adjacent ->
            val root = if (index in roots) "*" else ""
            append("$index$root:")
            if (index == adjacencyList.size - 1) {
                append(adjacent.joinToString(",", prefix = "[", postfix = "]"))
            }
            else append(adjacent.joinToString(",", prefix = "[", postfix = "],")).append("\n")
        }
        append("}")
    }
}
package nl.hannahschellekens.grill.algorithm

import nl.hannahschellekens.grill.graph.AdjacencyList
import nl.hannahschellekens.grill.graph.Edge
import nl.hannahschellekens.grill.graph.Matching
import nl.hannahschellekens.grill.graph.Path
import nl.hannahschellekens.grill.util.toOrdered
import nl.hannahschellekens.grill.util.toPair

/**
 * Calculates the maximum matching for a bipartite graph.
 *
 * @receiver The graph in adjacency list format to find a matching for. All vertices that are part of the same
 * partition must be numbered consecutively.
 * @param sizeFirstPartition The amount of vertices in the first partition.
 * @return All edges part of the maximum matching.
 */
fun AdjacencyList.maximumBipartiteMatching(sizeFirstPartition: Int): Set<Edge> {
    // Hopcroft-Karp Algorithm.
    val matching = HashSet<Edge>()
    val coveredVertices = HashSet<Int>()

    // First match every vertex in the first partition to their first candidate.
    for (v in 0 until sizeFirstPartition) {
        val adjacent = adjacentVertices(v)
        val possibleMatch = adjacent.firstOrNull { it !in coveredVertices } ?: continue

        // Because iteration starts from the first partition and the graph is bipartite,
        // the edge `(e1, e2)` always has `e1 < e2`
        matching.add(v to possibleMatch)
        coveredVertices += v
        coveredVertices += possibleMatch
    }

    // All vertices matched? We are already done. Good job.
    if (matching.size == sizeFirstPartition) return matching

    // Otherwise keep looking for new augmenting paths.
    var augmentingPaths: Set<Path>? = null
    while (augmentingPaths == null || augmentingPaths.isNotEmpty()) {
        augmentingPaths = findAugmentingPaths(matching, coveredVertices, sizeFirstPartition)
        flipAugmentingPathEdges(matching, augmentingPaths, coveredVertices)
    }

    return matching
}

/**
 * Finds all augmenting paths in this graph.
 */
private fun AdjacencyList.findAugmentingPaths(
    matching: Matching,
    coveredVertices: HashSet<Int>,
    sizeFirstPartition: Int
): Set<Path> {
    // Stores all available augmenting paths.
    val augmentingPaths = HashSet<Path>()

    // First do a BFS to find all possible paths to the level with the first uncovered vertices.
    val (levelGraph, vertexMap) = findAlternatingGraph(matching, coveredVertices, sizeFirstPartition)

    // Backtrack from the uncovered leaves to the root node with DFS to find the augmenting paths.
    // `pathNodes`: Stores all vertices that have been visited by the DFS backtracing.
    val pathNodes = HashSet<Int>()
    val leaves = levelGraph.findLeaves().filter { vertexMap[it] !in coveredVertices }

    // Depth first search on leaves to next uncovered vertex.
    // Stack stores pair (Vertex, Path up until this vertex)
    val stack = ArrayDeque<Pair<Int, Path>>()
    leaves.forEach { leaf ->
        if (leaf in pathNodes) return@forEach

        var resultPath: Path? = null
        val visited = mutableSetOf<Int>()
        stack.addFirst(
            leaf to listOf(vertexMap[leaf] ?: error("Leaf <$leaf> does not exist in vertexMap <$vertexMap>"))
        )

        while (stack.isNotEmpty()) {
            val (current, path) = stack.removeFirst()

            // If the path contains nodes that have already been traversed, stop execution.
            // This needs to be checked, because branched paths on the stack can have
            // an earlier branch already having found a correct path.
            if (path.any { it in pathNodes }) continue

            if (leaf != current && vertexMap[current] !in coveredVertices) {
                resultPath = path
                break
            }

            if (current !in visited) {
                visited.add(current)

                levelGraph.adjacentVertices(current).asSequence()
                    .filter { it !in visited && vertexMap[it] !in pathNodes }
                    // Make sure that matched and unmatched edges are alternated.
                    .filter {
                        if (path.size < 2) return@filter true
                        val previousPath = path.takeLast(2).sorted().toPair()
                        val nextPath = vertexMap[current]!! toOrdered vertexMap[it]!!
                        previousPath in matching != nextPath in matching
                    }
                    .forEach { neighbour ->
                        val realIndexInGraph = vertexMap[neighbour]
                            ?: error("Neighbour <$neighbour> does not exist in vertex map <$vertexMap>")
                        stack.addFirst(neighbour to (path + realIndexInGraph))
                    }
            }
        }

        resultPath?.let {
            pathNodes.addAll(it)
            augmentingPaths.add(it)
        }
    }

    return augmentingPaths
}

/**
 * Runs a BFS to find all possible paths from the starting vertices
 */
private fun AdjacencyList.findAlternatingGraph(
    matching: Matching,
    coveredVertices: HashSet<Int>,
    sizeFirstPartition: Int
): Pair<AdjacencyList, Map<Int, Int>> {
    // Maps the vertex in this adjacency list to that of the original graph.
    val vertexMap = HashMap<Int, Int>()
    val reverseVertexMap = HashMap<Int, Int>()

    return AdjacencyList(0).apply {
        // All nodes that have been visited by the BFS algorithm to prevent nodes being visited twice.
        val bfsVisitedNodes = HashSet<Int>()

        // Initialize first level of the graph.
        var currentLevel = 0
        var nodesInLevel = (0 until sizeFirstPartition).filter { it !in coveredVertices }.toSet()

        // Add root nodes to the graph
        nodesInLevel.forEach { parentNode ->
            val newVertex = addVertex(isRoot = true)
            vertexMap[newVertex] = parentNode
            reverseVertexMap[parentNode] = newVertex
        }

        // Execute BFS.
        while (nodesInLevel.isNotEmpty()) {
            val nodesInNextLevel = HashSet<Int>()
            bfsVisitedNodes.addAll(nodesInLevel)

            // Branch out for every node in this level.
            nodesInLevel.forEach { parentNode ->
                // Find which nodes we can branch out to. The edges must only be part of the matching
                // in even levels (starting from 0) to ensure the paths are alternating.
                val nextNodes = this@findAlternatingGraph.adjacentVertices(parentNode).filter {
                    (parentNode toOrdered it) !in matching == (currentLevel % 2 == 0) && it !in bfsVisitedNodes
                }

                // Find nodes to branch out to.
                nextNodes.forEach { nextNode ->
                    nodesInNextLevel.add(nextNode)

                    // Add newly discovered node to the graph and connect it to the parent(s).
                    val parentVertex = reverseVertexMap[parentNode] ?: addVertex()
                    vertexMap[parentVertex] = parentNode
                    reverseVertexMap[parentNode] = parentVertex

                    val nextVertex = reverseVertexMap[nextNode] ?: addVertex()
                    vertexMap[nextVertex] = nextNode
                    reverseVertexMap[nextNode] = nextVertex

                    addUndirectedEdge(nextVertex, parentVertex)
                }
            }

            // Advance 1 level.
            currentLevel++
            nodesInLevel = nodesInNextLevel
        }
    } to vertexMap
}

/**
 * Includes all edges from the augmenting paths that are not in the matching into the matching and
 * removes all edges from the matching that are in the augmenting paths but are already in the matching.
 */
private fun flipAugmentingPathEdges(
    matching: MutableSet<Edge>,
    augmentingPaths: Iterable<Path>,
    coveredVertices: HashSet<Int>
) {
    augmentingPaths.forEach { path ->
        for (i in 0 until path.size - 1) {
            val edge = path[i] toOrdered path[i + 1]
            if (edge in matching) {
                matching.remove(edge)
            }
            else {
                matching.add(edge)
                coveredVertices.add(edge.first)
                coveredVertices.add(edge.second)
            }
        }
    }
}
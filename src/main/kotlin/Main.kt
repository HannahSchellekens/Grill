package nl.hannahschellekens.grill

import nl.hannahschellekens.grill.model.*
import nl.hannahschellekens.grill.operations.max
import nl.hannahschellekens.grill.operations.min

/**
 * @author Hannah Schellekens
 */
fun main() {

    val A = IntMatrix(3, 3, IntArray(9) { it + 1 })
    println("[A] Dim: ${A.dimension}, Size: ${A.size}, Max: ${A.max()}, Min: ${A.min()}")
    println(A)
    println()

    val B = IntMatrix(3, 3, IntArray(9) { it + 10 })
    println("[B] Dim: ${B.dimension}, Size: ${B.size}, Max: ${B.max()}, Min: ${B.min()}")
    println(B)
    println()
}
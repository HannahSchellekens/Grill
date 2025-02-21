package nl.hannahschellekens.grill

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.operations.*

/**
 * @author Hannah Schellekens
 */
fun main() {

    val A = intMatrix(3, 3) { i -> i + 1 }
    println("[A] Dim: ${A.dimension}, Size: ${A.size}, Max: ${A.max()}, Min: ${A.min()}")
    println(A)
    println()

    val mutA = A.mutable!!

    mutA.swapRows(0, 2)
    mutA.swapColumns(1, 2)
    println(mutA)
}
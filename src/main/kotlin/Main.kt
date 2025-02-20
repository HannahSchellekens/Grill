package nl.hannahschellekens.grill

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.operations.*

/**
 * @author Hannah Schellekens
 */
fun main() {

    val A = intMatrix(4, 6) { i -> i + 1 }
    println("[A] Dim: ${A.dimension}, Size: ${A.size}, Max: ${A.max()}, Min: ${A.min()}")
    println(A)
    println()

    val mutA = A.mutable!!

    val col2 = intColumnVectorOf(99, 999, 9999, 99999, 999999, 9999999)
    val col4 = intColumnVectorOf(88, 888, 8888, 88888, 888888, 8888888)
    val valueMatrix = col2.hstack(col4)
    println(valueMatrix)
    println()

    val cols = intColumnVectorOf(1, 3)
    mutA.setColumnsFromMatrix(cols, valueMatrix)

    println(mutA)
    println()
}
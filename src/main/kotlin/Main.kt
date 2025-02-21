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

    val B = doubleMatrix(3, 3) { i -> i + 1.0 }
    println("[B] Dim: ${B.dimension}, Size: ${B.size}, Max: ${B.max()}, Min: ${B.min()}")
    println(B)
    println()

    val mutA = A.mutable!!
    val mutB = B.mutable!!

    val a = intMatrixOf(2, 2, 1, 3, -5, 2)
    val b = intRowVectorOf(4, -2, -1)

    println(a)
    println(b)

    println(a.dotProductInt(a))
}
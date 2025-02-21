package nl.hannahschellekens.grill

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.nl.hannahschellekens.grill.operations.power
import nl.hannahschellekens.grill.nl.hannahschellekens.grill.operations.powerInt
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

    println(B * B * B * B)
    println(B.power(4))
}
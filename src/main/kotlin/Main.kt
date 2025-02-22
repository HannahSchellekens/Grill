package nl.hannahschellekens.grill

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.operations.*
import nl.hannahschellekens.grill.view.view
import kotlin.random.Random

/**
 * @author Hannah Schellekens
 */
fun main() {

    val A = intMatrix(3, 3) { _ -> Random.nextInt(0, 3) }
    println("[A] Dim: ${A.dimension}, Size: ${A.size}, Max: ${A.max()}, Min: ${A.min()}")
    println(A)
    println()

    val B = doubleMatrixOf(3, 3, 2.0, -1.0, 0.0, -1.0, 2.0, -1.0, 0.0, -1.0, 2.0)
    println("[B] Dim: ${B.dimension}, Size: ${B.size}, Max: ${B.max()}, Min: ${B.min()}")
    println(B)
    println()

    val mutA = A.mutable!!
    val mutB = B.mutable!!
}
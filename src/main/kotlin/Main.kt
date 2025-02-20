package nl.hannahschellekens.grill

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.operations.max
import nl.hannahschellekens.grill.operations.min
import nl.hannahschellekens.grill.view.*

/**
 * @author Hannah Schellekens
 */
fun main() {

    val A = intMatrix(4, 6) { i -> i + 1 }
    println("[A] Dim: ${A.dimension}, Size: ${A.size}, Max: ${A.max()}, Min: ${A.min()}")
    println(A)
    println()

    val sqA = A.squareView(0xCAFE)
    println(sqA)
    println()
}
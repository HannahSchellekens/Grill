package nl.hannahschellekens.grill

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.operations.max
import nl.hannahschellekens.grill.operations.min
import nl.hannahschellekens.grill.view.JoinedView
import nl.hannahschellekens.grill.view.MatrixView
import nl.hannahschellekens.grill.view.view

/**
 * @author Hannah Schellekens
 */
fun main() {

    val A = IntMatrix(4, 6, IntArray(24) { it + 1 })
    println("[A] Dim: ${A.dimension}, Size: ${A.size}, Max: ${A.max()}, Min: ${A.min()}")
    println(A)
    println()

    val reshaped = A.reshape(6, 4)
    println("[reshaped] Dim: ${reshaped.dimension}, Size: ${reshaped.size}, Max: ${reshaped.max()}, Min: ${reshaped.min()}")
    println(reshaped)
    println()


}
package nl.hannahschellekens.grill

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.nl.hannahschellekens.grill.operations.*
import nl.hannahschellekens.grill.operations.*
import nl.hannahschellekens.grill.util.approxEquals
import nl.hannahschellekens.grill.util.approxZero
import nl.hannahschellekens.grill.view.view
import nl.hannahschellekens.grill.view.viewColumns
import nl.hannahschellekens.grill.view.viewRows
import kotlin.random.Random
import kotlin.system.measureTimeMillis

/**
 * @author Hannah Schellekens
 */
fun main() {

    val rand = Random(1234)
    val A = intMatrix(10000, 10000) { _ -> rand.nextInt(0, 5) }
    println("[A] Dim: ${A.dimension}, Size: ${A.size}, Max: ${A.max()}, Min: ${A.min()}")
    println(A)
    println()
}
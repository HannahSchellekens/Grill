package nl.hannahschellekens.grill

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.operations.max
import nl.hannahschellekens.grill.operations.min
import nl.hannahschellekens.grill.operations.set
import nl.hannahschellekens.grill.operations.setWhen
import kotlin.random.Random
import kotlin.system.measureTimeMillis

/**
 * @author Hannah Schellekens
 */
fun main() {

    val rand = Random(1234)
    val A = intMatrix(10, 10) { _ -> rand.nextInt(0, 5) }
    println("[A] Dim: ${A.dimension}, Size: ${A.size}, Max: ${A.max()}, Min: ${A.min()}")
    println(A)
    println()
}
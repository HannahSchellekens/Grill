package nl.hannahschellekens.grill

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.nl.hannahschellekens.grill.operations.angle
import nl.hannahschellekens.grill.nl.hannahschellekens.grill.operations.distance
import nl.hannahschellekens.grill.operations.*
import kotlin.math.PI
import kotlin.math.acos
import kotlin.math.sqrt
import kotlin.random.Random
import kotlin.system.measureTimeMillis

/**
 * @author Hannah Schellekens
 */
fun main() {

    val n = 5

    val v = intRowVectorOf(1, 2, 3, 4, 5)
    val w = intRowVectorOf(6, 7, 8, 9, 10)

    println("v: $v\nw: $w")

    println("||v|| = ${v.norm()}, ||w|| = ${w.norm()}")
    println("v.w = ${v.dotProductInt(w)}, vwT = ${v.multiplyInt(w.transposed())}")

    println("angle(v,w) = ${v.angle(w)}")

    println()

    val a = intRowVectorOf(1, 2, -3)
    val b = intRowVectorOf(1, 2, 3)

    println("a: $a, b: $b")
    println("||a||1 = ${a.normOne()}")
    println("||a||2 = ${a.normEuclidean()}")
    println("||a||inf = ${a.normInfinity()}")
    println("||a - b|| = ${a.toDoubleMatrix().distance(b.toDoubleMatrix())}")
}
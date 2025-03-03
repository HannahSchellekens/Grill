package nl.hannahschellekens.grill.nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.Matrix
import nl.hannahschellekens.grill.matrix.checkVector
import nl.hannahschellekens.grill.operations.dotProduct
import nl.hannahschellekens.grill.operations.dotProductInt
import nl.hannahschellekens.grill.operations.norm
import kotlin.math.acos

fun Matrix<Int>.angleInt(other: Matrix<Int>): Double {
    checkVector()
    val dot = dotProductInt(other)
    val norms = norm() * other.norm()
    return acos(dot.toDouble() / norms)
}

fun <T : Number, U : Number> Matrix<T>.angle(other: Matrix<U>): Double {
    checkVector()
    val dot = dotProduct(other)
    val norms = norm() * other.norm()
    return acos(dot / norms)
}
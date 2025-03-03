package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*
import kotlin.math.abs
import kotlin.math.sqrt

fun <T : Number> Matrix<T>.normOne(): Double {
    checkVector()

    var sum = 0.0
    for (i in 0 until size) {
        sum += abs(this[i].toDouble())
    }
    return sum
}

fun <T : Number> Matrix<T>.normEuclidean(): Double {
    checkVector()

    var sumOfSquares = 0.0
    for (i in 0 until size) {
        val element = this[i]
        sumOfSquares += (element.toDouble() * element.toDouble())
    }
    return sqrt(sumOfSquares)
}

fun <T : Number> Matrix<T>.normInfinity(): Double {
    checkVector()

    var max = 0.0
    for (i in 0 until size) {
        val element = abs(this[i].toDouble())
        max = if (element > max) element else max
    }
    return max
}

fun Matrix<Double>.normBasis(basis: Matrix<Double>): Double {
    return sqrt(basis.multiply(this).dotProduct(this))
}

fun <T : Number> Matrix<T>.norm() = normEuclidean()
package nl.hannahschellekens.grill.nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.operations.multiplyInt
import nl.hannahschellekens.grill.operations.times
import nl.hannahschellekens.grill.view.viewColumn
import nl.hannahschellekens.grill.view.viewRow
import kotlin.math.exp

fun Matrix<Int>.powerInt(exponent: Int, filledIdentity: Boolean = false): Matrix<Int> {
    check(isSquare) { "Matrix is not square, got <$dimension>" }

    if (exponent < 0) {
        TODO("Inverses are not yet implemented.")
    }

    return when (exponent) {
        0 -> if (filledIdentity) intIdentity(width).toMatrix() else intIdentity(width)
        1 -> this
        2 -> multiplyInt(this)
        else -> {
            var result = this
            for (i in 2..exponent) {
                result = result.multiplyInt(this)
            }
            result
        }
    }
}

fun Matrix<Double>.power(exponent: Int, filledIdentity: Boolean = false): Matrix<Double> {
    check(isSquare) { "Matrix is not square, got <$dimension>" }

    if (exponent < 0) {
        TODO("Inverses are not yet implemented.")
    }

    return when (exponent) {
        0 -> if (filledIdentity) doubleIdentity(width).toMatrix() else doubleIdentity(width)
        1 -> this
        2 -> this * this
        else -> {
            var result = this
            for (i in 2..exponent) {
                result *= this
            }
            result
        }
    }
}
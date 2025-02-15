package nl.hannahschellekens.grill.matrix

import kotlin.math.abs

val <T> Matrix<T>.size: Int
    get() = width * height

val <T> Matrix<T>.dimension: Dimension
    get() = Dimension(height, width)

val <T> Matrix<T>.isSquare: Boolean
    get() = width == height

val <T> Matrix<T>.elementIndices: IntRange
    get() = 0 until size

val <T> Matrix<T>.rowIndices: IntRange
    get() = 0 until height

val <T> Matrix<T>.columnIndices: IntRange
    get() = 0 until width

fun <T> Matrix<T>.isEmpty(): Boolean = size == 0

fun <T> Matrix<T>.isIdentity(checkOne: (T) -> Boolean, checkZero: (T) -> Boolean): Boolean = when {
    this is IntIdentity || this is DoubleIdentity -> true
    isSquare.not() -> false
    else -> {
        rowIndices.forEach { rowIndex ->
            columnIndices.forEach { colIndex ->
                val element = this[rowIndex, colIndex]
                if (rowIndex == colIndex && checkOne(element).not()) {
                    return false
                }
                else if (rowIndex != colIndex && checkZero(element).not()) {
                    return false
                }
            }
        }
        true
    }
}

fun Matrix<Int>.isIdentity(): Boolean = isIdentity({ it == 1 }, { it == 0 })

fun Matrix<Double>.isIdentity(epsilon: Double = 10e-9): Boolean = isIdentity(checkOne = {
    abs(it - 1) < epsilon
}, checkZero = {
    abs(it) < epsilon
})
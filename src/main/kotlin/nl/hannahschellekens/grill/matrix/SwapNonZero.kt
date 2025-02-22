package nl.hannahschellekens.grill.matrix

import nl.hannahschellekens.grill.util.DEFAULT_EPSILON
import nl.hannahschellekens.grill.util.approxZero

fun MutableMatrix<Int>.swapToNonZero(row: Int, col: Int): MutableMatrix<Int> {
    if (this[row, col] != 0) return this

    for (i in (row + 1) until height) {
        if (this[i, col] != 0) {
            swapRows(row, i)
            return this
        }
    }
    return this
}

fun MutableMatrix<Double>.swapToNonZero(row: Int, col: Int, epsilon: Double = DEFAULT_EPSILON): MutableMatrix<Double> {
    if (this[row, col].approxZero(epsilon).not()) return this

    for (i in (row + 1) until height) {
        if (this[i, col].approxZero(epsilon).not()) {
            swapRows(row, i)
            return this
        }
    }
    return this
}
package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.MutableMatrix

/**
 * Per row: subtracts the minimum of the row from all values in the row.
 */
fun MutableMatrix<Int>.subtractRowMinimaInt() {
    for (row in 0 until height) {
        val minimum = (0 until width).minOf { col -> this[row, col] }
        for (col in 0 until width) {
            this[row, col] -= minimum
        }
    }
}

/**
 * Per row: subtracts the minimum of the row from all values in the row.
 */
fun MutableMatrix<Double>.subtractRowMinima() {
    for (row in 0 until height) {
        val minimum = (0 until width).minOf { col -> this[row, col] }
        for (col in 0 until width) {
            this[row, col] -= minimum
        }
    }
}

/**
 * Per column: subtracts the minimum of the column from all values in the columns.
 */
fun MutableMatrix<Int>.subtractColumnMinimaInt() {
    for (col in 0 until width) {
        val minimum = (0 until height).minOf { row -> this[row, col] }
        for (row in 0 until height) {
            this[row, col] -= minimum
        }
    }
}

/**
 * Per column: subtracts the minimum of the column from all values in the columns.
 */
fun MutableMatrix<Double>.subtractColumnMinima() {
    for (col in 0 until width) {
        val minimum = (0 until height).minOf { row -> this[row, col] }
        for (row in 0 until height) {
            this[row, col] -= minimum
        }
    }
}
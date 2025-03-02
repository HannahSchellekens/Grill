package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.view.viewColumn
import nl.hannahschellekens.grill.view.viewRow

inline fun Matrix<Int>.mapInt(transform: (Int) -> Int): Matrix<Int> {
    return toMatrix().setAll(transform)
}

inline fun Matrix<Double>.map(transform: (Double) -> Double): Matrix<Double> {
    return toMatrix().setAll(transform)
}

inline fun Matrix<Int>.mapRowsInt(transform: (row: Matrix<Int>) -> Matrix<Int>): Matrix<Int> {
    val result = toMatrix()
    for (row in 0 until height) {
        val resultRow = transform(viewRow(row))
        for (col in 0 until width) {
            result[row, col] = resultRow[0, col]
        }
    }
    return result
}

inline fun Matrix<Double>.mapRows(transform: (row: Matrix<Double>) -> Matrix<Double>): Matrix<Double> {
    val result = toMatrix()
    for (row in 0 until height) {
        val resultRow = transform(viewRow(row))
        for (col in 0 until width) {
            result[row, col] = resultRow[0, col]
        }
    }
    return result
}

inline fun Matrix<Int>.mapColumnsInt(transform: (column: Matrix<Int>) -> Matrix<Int>): Matrix<Int> {
    val result = toMatrix()
    for (col in 0 until width) {
        val resultColumn = transform(viewColumn(col))
        for (row in 0 until height) {
            result[row, col] = resultColumn[row, 0]
        }
    }
    return result
}

inline fun Matrix<Double>.mapColumns(transform: (column: Matrix<Double>) -> Matrix<Double>): Matrix<Double> {
    val result = toMatrix()
    for (col in 0 until width) {
        val resultColumn = transform(viewColumn(col))
        for (row in 0 until height) {
            result[row, col] = resultColumn[row, 0]
        }
    }
    return result
}
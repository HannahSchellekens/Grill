package nl.hannahschellekens.grill.view

import nl.hannahschellekens.grill.matrix.Matrix
import nl.hannahschellekens.grill.util.size

fun <T> Matrix<T>.view(rows: IntRange, cols: IntRange): View<T> {
    return MatrixView(this, rows.first * width + cols.first, cols.size, rows.size)
}

operator fun <T> Matrix<T>.get(rows: IntRange, cols: IntRange) = view(rows, cols)

fun <T> Matrix<T>.viewColumns(startColumn: Int): View<T> {
    return MatrixView(this, startColumn, width - startColumn, height)
}

fun <T> Matrix<T>.viewRows(startRow: Int): View<T> {
    return MatrixView(this, startRow * width, width, height - startRow)
}

fun <T> Matrix<T>.viewColumnsUntil(endColumnExclusive: Int): View<T> {
    return MatrixView(this, 0, endColumnExclusive, height)
}

fun <T> Matrix<T>.viewRowsUntil(endRowExclusive: Int): View<T> {
    return MatrixView(this, 0, width, endRowExclusive)
}

fun <T> Matrix<T>.viewRow(row: Int): View<T> {
    return MatrixView(this, row * width, width, 1)
}

fun <T> Matrix<T>.viewColumn(col: Int): View<T> {
    return MatrixView(this, col, 1, height)
}
package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*

fun MutableMatrix<Int>.addRowIntModify(rowIndex: Int, row: Matrix<Int>, scalar: Int = 1): MutableMatrix<Int> {
    check(rowIndex in rowIndices) { "Row <$row> out of bounds: <$dimension>" }
    check(row.height == 1 && width == row.width) {
        "Row dimension <${row.dimension}> does not match <Dim(1x$width)>"
    }

    for (col in columnIndices) {
        this[rowIndex, col] = this[rowIndex, col] + scalar * row[0, col]
    }
    return this
}

fun Matrix<Int>.addRowInt(rowIndex: Int, row: Matrix<Int>, scalar: Int = 1): Matrix<Int> {
    return toMatrix().addRowIntModify(rowIndex, row, scalar)
}

fun MutableMatrix<Int>.addRowFromMatrixIntModify(rowIndex: Int, rowToAddIndex: Int, scalar: Int = 1): MutableMatrix<Int> {
    for (col in columnIndices) {
        this[rowIndex, col] = this[rowIndex, col] + scalar * this[rowToAddIndex, col]
    }
    return this
}

fun Matrix<Int>.addRowFromMatrixInt(rowIndex: Int, rowToAddIndex: Int, scalar: Int = 1): Matrix<Int> {
    return toMatrix().addRowFromMatrixIntModify(rowIndex, rowToAddIndex, scalar)
}

fun MutableMatrix<Double>.addRowModify(rowIndex: Int, row: Matrix<Double>, scalar: Double = 1.0): MutableMatrix<Double> {
    check(rowIndex in rowIndices) { "Row <$row> out of bounds: <$dimension>" }
    check(row.height == 1 && width == row.width) {
        "Row dimension <${row.dimension}> does not match <Dim(1x$width)>"
    }

    for (col in columnIndices) {
        this[rowIndex, col] = this[rowIndex, col] + scalar * row[0, col]
    }
    return this
}

fun Matrix<Double>.addRow(rowIndex: Int, row: Matrix<Double>, scalar: Double = 1.0): Matrix<Double> {
    return toMatrix().addRowModify(rowIndex, row, scalar)
}

fun MutableMatrix<Double>.addRowFromMatrixModify(rowIndex: Int, rowToAddIndex: Int, scalar: Double = 1.0): MutableMatrix<Double> {
    for (col in columnIndices) {
        this[rowIndex, col] = this[rowIndex, col] + scalar * this[rowToAddIndex, col]
    }
    return this
}

fun Matrix<Double>.addRowFromMatrix(rowIndex: Int, rowToAddIndex: Int, scalar: Double = 1.0): Matrix<Double> {
    return toMatrix().addRowFromMatrixModify(rowIndex, rowToAddIndex, scalar)
}
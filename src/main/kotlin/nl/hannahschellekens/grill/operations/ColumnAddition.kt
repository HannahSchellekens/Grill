package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*

fun MutableMatrix<Int>.addColumnIntModify(colIndex: Int, col: Matrix<Int>, scalar: Int = 1): MutableMatrix<Int> {
    check(colIndex in columnIndices) { "Column <$col> out of bounds: <$dimension>" }
    check(col.width == 1 && height == col.height) {
        "Column dimension <${col.dimension}> does not match <Dim(${height}x1)>"
    }

    for (row in rowIndices) {
        this[row, colIndex] = this[row, colIndex] + scalar * col[row, 0]
    }
    return this
}

fun Matrix<Int>.addColumnInt(colIndex: Int, col: Matrix<Int>, scalar: Int = 1): Matrix<Int> {
    return toMatrix().addColumnIntModify(colIndex, col, scalar)
}

fun MutableMatrix<Int>.addColumnIntModify(colIndex: Int, colToAddIndex: Int, scalar: Int = 1): MutableMatrix<Int> {
    for (row in rowIndices) {
        this[row, colIndex] = this[row, colIndex] + scalar * this[row, colToAddIndex]
    }
    return this
}

fun Matrix<Int>.addColumnInt(colIndex: Int, colToAddIndex: Int, scalar: Int = 1): Matrix<Int> {
    return toMatrix().addColumnIntModify(colIndex, colToAddIndex, scalar)
}

fun MutableMatrix<Double>.addColumnModify(colIndex: Int, col: Matrix<Double>, scalar: Double = 1.0): MutableMatrix<Double> {
    check(colIndex in columnIndices) { "Column <$col> out of bounds: <$dimension>" }
    check(col.width == 1 && height == col.height) {
        "Column dimension <${col.dimension}> does not match <Dim(${height}x1)>"
    }

    for (row in rowIndices) {
        this[row, colIndex] = this[row, colIndex] + scalar * col[row, 0]
    }
    return this
}

fun Matrix<Double>.addColumnInt(colIndex: Int, col: Matrix<Double>, scalar: Double = 1.0): Matrix<Double> {
    return toMatrix().addColumnModify(colIndex, col, scalar)
}

fun MutableMatrix<Double>.addColumnModify(colIndex: Int, colToAddIndex: Int, scalar: Double = 1.0): MutableMatrix<Double> {
    for (row in rowIndices) {
        this[row, colIndex] = this[row, colIndex] + scalar * this[row, colToAddIndex]
    }
    return this
}

fun Matrix<Double>.addColumn(colIndex: Int, colToAddIndex: Int, scalar: Double = 1.0): Matrix<Double> {
    return toMatrix().addColumnModify(colIndex, colToAddIndex, scalar)
}

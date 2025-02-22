package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*

fun MutableMatrix<Int>.scalarModify(scalar: Int): MutableMatrix<Int> = setAll { it * scalar }

fun MutableMatrix<Double>.scalarModify(scalar: Double): MutableMatrix<Double> = setAll { it * scalar }

operator fun MutableMatrix<Int>.timesAssign(scalar: Int) {
    scalarModify(scalar)
}

operator fun MutableMatrix<Double>.timesAssign(scalar: Double) {
    scalarModify(scalar)
}

fun Matrix<Int>.scalarInt(scalar: Int): Matrix<Int> {
    return intMatrix(width, height) { it -> this[it] * scalar }
}

fun Matrix<Double>.scalar(scalar: Double): Matrix<Double> {
    return doubleMatrix(width, height) { it -> this[it] * scalar }
}

operator fun Matrix<Double>.times(scalar: Int) = scalar(scalar.toDouble())
operator fun Int.times(other: Matrix<Double>) = other.scalar(toDouble())
operator fun Matrix<Double>.times(scalar: Double) = scalar(scalar)
operator fun Double.times(other: Matrix<Double>) = other.scalar(this)

fun MutableMatrix<Int>.scalarRowModify(row: Int, scalar: Int): MutableMatrix<Int> {
    check(row in rowIndices) { "Row <$row> out of bounds: <$dimension>" }

    for (col in columnIndices) {
        this[row, col] = this[row, col] * scalar
    }
    return this
}

fun Matrix<Int>.scalarRow(row: Int, scalar: Int): Matrix<Int> {
    return toMatrix().scalarRowModify(row, scalar)
}

fun MutableMatrix<Int>.scalarColumnModify(col: Int, scalar: Int): MutableMatrix<Int> {
    check(col in columnIndices) { "Column <$col> out of bounds: <$dimension>" }

    for (row in rowIndices) {
        this[row, col] = this[row, col] * scalar
    }
    return this
}

fun Matrix<Int>.scalarColumn(col: Int, scalar: Int): Matrix<Int> {
    return toMatrix().scalarColumnModify(col, scalar)
}

fun MutableMatrix<Double>.scalarRowModify(row: Int, scalar: Double): MutableMatrix<Double> {
    check(row in rowIndices) { "Row <$row> out of bounds: <$dimension>" }

    for (col in columnIndices) {
        this[row, col] = this[row, col] * scalar
    }
    return this
}

fun Matrix<Double>.scalarRow(row: Int, scalar: Double): Matrix<Double> {
    return toMatrix().scalarRowModify(row, scalar)
}

fun MutableMatrix<Double>.scalarColumnModify(col: Int, scalar: Double): MutableMatrix<Double> {
    check(col in columnIndices) { "Column <$col> out of bounds: <$dimension>" }

    for (row in rowIndices) {
        this[row, col] = this[row, col] * scalar
    }
    return this
}

fun Matrix<Double>.scalarColumn(col: Int, scalar: Double): Matrix<Double> {
    return toMatrix().scalarColumnModify(col, scalar)
}

fun MutableMatrix<Int>.negateIntModify(): MutableMatrix<Int> = scalarModify(-1)

fun Matrix<Int>.negateInt(): Matrix<Int> {
    return intMatrix(width, height) { it -> -this[it] }
}

fun MutableMatrix<Double>.negateModify(): MutableMatrix<Double> = setAll { -it }

fun Matrix<Double>.negate(): Matrix<Double> {
    return doubleMatrix(width, height) { it -> -this[it] }
}

operator fun Matrix<Double>.unaryMinus() = negate()
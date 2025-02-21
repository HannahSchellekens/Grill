package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*

fun MutableMatrix<Int>.subtractIntModify(other: Matrix<Int>): MutableMatrix<Int> {
    return pointwiseModify(other) { a, b -> a - b }
}

fun MutableMatrix<Double>.subtractModify(other: Matrix<Double>): MutableMatrix<Double> {
    return pointwiseModify(other) { a, b -> a - b }
}

operator fun MutableMatrix<Double>.minusAssign(other: Matrix<Double>) {
    subtractModify(other)
}

fun Matrix<Int>.subtractInt(other: Matrix<Int>): Matrix<Int> {
    checkDimensions(other)
    return intMatrix(width, height) { it -> this[it] - other[it] }
}

fun Matrix<Double>.subtract(other: Matrix<Double>): Matrix<Double> {
    checkDimensions(other)
    return doubleMatrix(width, height) { it -> this[it] - other[it] }
}

operator fun Matrix<Double>.minus(other: Matrix<Double>): Matrix<Double> = subtract(other)
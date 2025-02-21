package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*

fun MutableMatrix<Int>.elementDivideIntModify(other: Matrix<Int>): MutableMatrix<Int> {
    return pointwiseModify(other) { a, b -> a / b }
}

fun MutableMatrix<Double>.elementDivideModify(other: Matrix<Double>): MutableMatrix<Double> {
    return pointwiseModify(other) { a, b -> a / b }
}

fun Matrix<Int>.elementDivideInt(other: Matrix<Int>): Matrix<Int> {
    checkDimensions(other)
    return intMatrix(width, height) { it -> this[it] / other[it] }
}

fun Matrix<Double>.elementDivide(other: Matrix<Double>): Matrix<Double> {
    checkDimensions(other)
    return doubleMatrix(width, height) { it -> this[it] / other[it] }
}
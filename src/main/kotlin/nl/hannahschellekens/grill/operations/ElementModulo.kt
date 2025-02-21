package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*

fun MutableMatrix<Int>.elementModuloIntModify(other: Matrix<Int>): MutableMatrix<Int> {
    return pointwiseModify(other) { a, b -> a % b }
}

fun MutableMatrix<Double>.elementModuloModify(other: Matrix<Double>): MutableMatrix<Double> {
    return pointwiseModify(other) { a, b -> a % b }
}

fun Matrix<Int>.elementModuloInt(other: Matrix<Int>): Matrix<Int> {
    checkDimensions(other)
    return intMatrix(width, height) { it -> this[it] % other[it] }
}

fun Matrix<Double>.elementModulo(other: Matrix<Double>): Matrix<Double> {
    checkDimensions(other)
    return doubleMatrix(width, height) { it -> this[it] % other[it] }
}
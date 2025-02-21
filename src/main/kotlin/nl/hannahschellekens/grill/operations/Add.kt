package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*

fun MutableMatrix<Int>.addIntModify(other: Matrix<Int>): MutableMatrix<Int> {
    return pointwiseModify(other) { a, b -> a + b }
}

fun MutableMatrix<Double>.addModify(other: Matrix<Double>): MutableMatrix<Double> {
    return pointwiseModify(other) { a, b -> a + b }
}

operator fun MutableMatrix<Double>.plusAssign(other: Matrix<Double>) {
    addModify(other)
}

fun Matrix<Int>.addInt(other: Matrix<Int>): Matrix<Int> {
    checkDimensions(other)
    return intMatrix(width, height) { it -> this[it] + other[it] }
}

fun Matrix<Double>.add(other: Matrix<Double>): Matrix<Double> {
    checkDimensions(other)
    return doubleMatrix(width, height) { it -> this[it] + other[it] }
}

operator fun Matrix<Double>.plus(other: Matrix<Double>): Matrix<Double> = add(other)
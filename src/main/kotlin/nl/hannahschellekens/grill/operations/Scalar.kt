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

fun MutableMatrix<Int>.negateIntModify(): MutableMatrix<Int> = scalarModify(-1)

fun Matrix<Int>.negateInt(): Matrix<Int> {
    return intMatrix(width, height) { it -> -this[it] }
}

fun MutableMatrix<Double>.negateModify(): MutableMatrix<Double> = setAll { -it }

fun Matrix<Double>.negate(): Matrix<Double> {
    return doubleMatrix(width, height) { it -> -this[it] }
}

operator fun Matrix<Double>.unaryMinus() = negate()
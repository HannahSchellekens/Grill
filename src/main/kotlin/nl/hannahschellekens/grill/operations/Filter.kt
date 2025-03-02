package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*

inline fun Matrix<Int>.filterIntRows(predicate: (row: Matrix<Int>) -> Boolean): Matrix<Int> {
    val retained = rows().filter(predicate).flatMap { it.elements() }
    return IntMatrix(width, retained.size / width, retained.toIntArray())
}

inline fun Matrix<Double>.filterRows(predicate: (row: Matrix<Double>) -> Boolean): Matrix<Double> {
    val retained = rows().filter(predicate).flatMap { it.elements() }
    return DoubleMatrix(width, retained.size / width, retained.toDoubleArray())
}

inline fun Matrix<Int>.filterIntColumns(predicate: (column: Matrix<Int>) -> Boolean): Matrix<Int> {
    val retained = columns().filter(predicate).flatMap { it.elements() }
    return IntMatrix(retained.size / height, height, retained.toIntArray())
}

inline fun Matrix<Double>.filterColumns(predicate: (column: Matrix<Double>) -> Boolean): Matrix<Double> {
    val retained = columns().filter(predicate).flatMap { it.elements() }
    return DoubleMatrix(retained.size / height, height, retained.toDoubleArray())
}
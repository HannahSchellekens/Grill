package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.Matrix
import nl.hannahschellekens.grill.matrix.reshape
import nl.hannahschellekens.grill.matrix.reshapeInt
import nl.hannahschellekens.grill.matrix.size

fun Matrix<Int>.flattenRowsInt(): Matrix<Int> = reshapeInt(size, 1)

fun Matrix<Double>.flattenRows(): Matrix<Double> = reshape(size, 1)

fun Matrix<Int>.flattenColumnsInt(): Matrix<Int> = reshapeInt(1, height)

fun Matrix<Double>.flattenColumns(): Matrix<Double> = reshape(1, height)
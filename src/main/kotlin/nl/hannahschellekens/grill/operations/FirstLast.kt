package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.Matrix
import nl.hannahschellekens.grill.matrix.get
import nl.hannahschellekens.grill.matrix.size
import nl.hannahschellekens.grill.view.View
import nl.hannahschellekens.grill.view.viewColumn
import nl.hannahschellekens.grill.view.viewRow

fun <T> Matrix<T>.firstElement(): T = this[0]

fun <T> Matrix<T>.firstRow(): View<T> = viewRow(0)

fun <T> Matrix<T>.firstColumn(): View<T> = viewColumn(0)

fun <T> Matrix<T>.lastElement(): T = this[size - 1]

fun <T> Matrix<T>.lastRow(): View<T> = viewRow(height - 1)

fun <T> Matrix<T>.lastColumn(): View<T> = viewColumn(width - 1)
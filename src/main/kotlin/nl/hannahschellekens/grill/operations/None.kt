package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.Matrix
import nl.hannahschellekens.grill.matrix.columns
import nl.hannahschellekens.grill.matrix.elements
import nl.hannahschellekens.grill.matrix.rows

inline fun <T> Matrix<T>.noneElements(predicate: (element: T) -> Boolean): Boolean = elements().none(predicate)

inline fun <T> Matrix<T>.noneRows(predicate: (row: Matrix<T>) -> Boolean): Boolean = rows().none(predicate)

inline fun <T> Matrix<T>.noneColumns(predicate: (column: Matrix<T>) -> Boolean): Boolean = columns().none(predicate)
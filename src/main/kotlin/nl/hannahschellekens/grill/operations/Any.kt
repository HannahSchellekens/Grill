package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.Matrix
import nl.hannahschellekens.grill.matrix.columns
import nl.hannahschellekens.grill.matrix.elements
import nl.hannahschellekens.grill.matrix.rows

inline fun <T> Matrix<T>.anyElement(predicate: (element: T) -> Boolean): Boolean = elements().any(predicate)

inline fun <T> Matrix<T>.anyRow(predicate: (row: Matrix<T>) -> Boolean): Boolean = rows().any(predicate)

inline fun <T> Matrix<T>.anyColumn(predicate: (column: Matrix<T>) -> Boolean): Boolean = columns().any(predicate)
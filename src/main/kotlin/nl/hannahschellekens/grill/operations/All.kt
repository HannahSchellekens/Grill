package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.Matrix
import nl.hannahschellekens.grill.matrix.columns
import nl.hannahschellekens.grill.matrix.elements
import nl.hannahschellekens.grill.matrix.rows

inline fun <T> Matrix<T>.allElements(predicate: (element: T) -> Boolean): Boolean = elements().all(predicate)

inline fun <T> Matrix<T>.allRows(predicate: (row: Matrix<T>) -> Boolean): Boolean = rows().all(predicate)

inline fun <T> Matrix<T>.allColumns(predicate: (column: Matrix<T>) -> Boolean): Boolean = columns().all(predicate)
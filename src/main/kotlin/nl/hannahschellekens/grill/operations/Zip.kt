package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*
import kotlin.math.min

inline fun <T, R, V> Matrix<T>.zipElements(other: Matrix<R>, transform: (a: T, b: R) -> V): List<V> {
    return elements().zip(other.elements(), transform)
}

infix fun <T, R> Matrix<T>.zipElements(other: Matrix<R>): List<Pair<T, R>> {
    return zipElements(other) { t, r -> t to r }
}

inline fun <T, R, V> Matrix<T>.zipRows(other: Matrix<R>, transform: (a: Matrix<T>, b: Matrix<R>) -> V): List<V> {
    return rows().zip(other.rows(), transform)
}

infix fun <T, R> Matrix<T>.zipRows(other: Matrix<R>): List<Pair<Matrix<T>, Matrix<R>>> {
    return zipRows(other) { t, r -> t to r }
}

inline fun <T, R, V> Matrix<T>.zipColumns(other: Matrix<R>, transform: (a: Matrix<T>, b: Matrix<R>) -> V): List<V> {
    return columns().zip(other.columns(), transform)
}

infix fun <T, R> Matrix<T>.zipColumns(other: Matrix<R>): List<Pair<Matrix<T>, Matrix<R>>> {
    return zipColumns(other) { t, r -> t to r }
}
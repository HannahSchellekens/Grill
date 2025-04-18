package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*

inline fun <T> Matrix<T>.extremum(compare: (T, T) -> Int): T {
    check(size > 0) { "Matrix size must be greater than zero, got <$size>." }
    if (size == 1) return this[0, 0]

    var largestElement = this[0, 0]
    for (i in 1 until size) {
        val element = this[i]
        if (compare(element, largestElement) > 0) {
            largestElement = element
        }
    }
    return largestElement
}

fun <T : Comparable<T>> Matrix<T>.max() = extremum { a, b -> a.compareTo(b) }
fun <T : Comparable<T>> Matrix<T>.min() = extremum { a, b -> b.compareTo(a) }

inline fun <T> Matrix<T>.extremumIf(compare: (T, T) -> Int, predicate: (row: Int, col: Int) -> Boolean): T? {
    check(size > 0) { "Matrix size must be greater than zero, got <$size>." }
    if (size == 1) return this[0, 0]

    var largestElement: T? = null
    forRowAndColumns { row, col ->
        if (predicate(row, col).not()) return@forRowAndColumns
        val element = this[row, col]
        if (largestElement == null || compare(element, largestElement!!) > 0) {
            largestElement = element
        }
    }
    return largestElement
}

inline fun <T : Comparable<T>> Matrix<T>.maxIf(predicate: (row: Int, col: Int) -> Boolean): T? {
    return extremumIf({ a, b -> a.compareTo(b) }, predicate)
}

inline fun <T : Comparable<T>> Matrix<T>.minIf(predicate: (row: Int, col: Int) -> Boolean): T? {
    return extremumIf({ a, b -> b.compareTo(a) }, predicate)
}
package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.model.*

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
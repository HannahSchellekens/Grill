package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*

inline fun <T, U> MutableMatrix<T>.pointwiseModify(other: Matrix<U>, operation: (T, U) -> T): MutableMatrix<T> {
    checkDimensions(other)

    for (i in 0 until other.size) {
        set(i, operation(this[i], other[i]))
    }
    return this
}
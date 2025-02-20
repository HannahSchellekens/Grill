package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*

inline fun <T> MutableMatrix<T>.setValue(row: Int, col: Int, valueTransform: (T) -> T): MutableMatrix<T> {
    set(row, col, valueTransform(this[row, col]))
    return this
}

operator fun <T> MutableMatrix<T>.set(row: Int, col: Int, matrix: Matrix<T>): MutableMatrix<T> {
    this[row, col] = matrix[row, col]
    return this
}

inline fun <T> MutableMatrix<T>.setWhen(condition: (T) -> Boolean, value: T): MutableMatrix<T> {
    forRowAndColumns { row, col ->
        val element = this[row, col]
        if (condition(element)) {
            set(row, col, value)
        }
    }
    return this
}

inline operator fun <T> MutableMatrix<T>.set(condition: (T) -> Boolean, value: T) = setWhen(condition, value)

inline fun <T> MutableMatrix<T>.setWhen(
    condition: (T) -> Boolean,
    valueTransform: (originalValue: T) -> T
): MutableMatrix<T> {
    forRowAndColumns { row, col ->
        val element = this[row, col]
        if (condition(element)) {
            set(row, col, valueTransform(element))
        }
    }
    return this
    // No operator variant as type <(T)->Boolean, (T)->T> clashes with <(T)->Boolean, T>.
}

inline fun <T> MutableMatrix<T>.setWhen(condition: (T) -> Boolean, matrix: Matrix<T>): MutableMatrix<T> {
    forRowAndColumns { row, col ->
        val element = this[row, col]
        if (condition(element)) {
            set(row, col, matrix[row, col])
        }
    }
    return this
}

inline operator fun <T> MutableMatrix<T>.set(condition: (T) -> Boolean, value: Matrix<T>): MutableMatrix<T> {
    return setWhen(condition, value)
}
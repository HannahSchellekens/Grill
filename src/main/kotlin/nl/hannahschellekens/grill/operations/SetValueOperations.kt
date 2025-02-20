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

fun <T> MutableMatrix<T>.setRows(value: T, vararg rows: Int): MutableMatrix<T> {
    for (row in rows) {
        for (col in columnIndices) {
            this[row, col] = value
        }
    }
    return this
}

fun <T> MutableMatrix<T>.setColumns(value: T, vararg cols: Int): MutableMatrix<T> {
    for (row in rowIndices) {
        for (col in cols) {
            this[row, col] = value
        }
    }
    return this
}

inline fun <T> MutableMatrix<T>.setRows(vararg rows: Int, valueTransform: (originalValue: T) -> T): MutableMatrix<T> {
    for (row in rows) {
        for (col in columnIndices) {
            this[row, col] = valueTransform(this[row, col])
        }
    }
    return this
}

inline fun <T> MutableMatrix<T>.setColumns(vararg cols: Int, valueTransform: (originalValue: T) -> T): MutableMatrix<T> {
    for (row in rowIndices) {
        for (col in cols) {
            this[row, col] = valueTransform(this[row, col])
        }
    }
    return this
}

fun <T> MutableMatrix<T>.setRows(rowIndices: Matrix<Int>, vararg rowVectors: Matrix<T>): MutableMatrix<T> {
    check(rowIndices.height == 1 || rowIndices.width == 1) { "Must be a column or row vector, got <$dimension>" }

    val rowIndexVector = if (rowIndices.height == 1) rowIndices else rowIndices.transposed()

    for (index in rowIndexVector.columnIndices) {
        val rowIndex = rowIndices[index]
        val rowValue = rowVectors[index]

        for (col in columnIndices) {
            this[rowIndex, col] = rowValue[0, col]
        }
    }
    return this
}

fun <T> MutableMatrix<T>.setRowsFromMatrix(rowIndices: Matrix<Int>, rowMatrix: Matrix<T>): MutableMatrix<T> {
    check(rowIndices.height == 1 || rowIndices.width == 1) {
        "Must be a column or row vector, got <$dimension>"
    }

    val rowIndexVector = if (rowIndices.height == 1) rowIndices else rowIndices.transposed()

    for (index in rowIndexVector.columnIndices) {
        val rowIndex = rowIndices[index]

        for (col in columnIndices) {
            this[rowIndex, col] = rowMatrix[index, col]
        }
    }
    return this
}

fun <T> MutableMatrix<T>.setColumns(columnIndices: Matrix<Int>, vararg columnVectors: Matrix<T>): MutableMatrix<T> {
    check(columnIndices.height == 1 || columnIndices.width == 1) {
        "Must be a column or row vector, got <$dimension>"
    }

    val columnIndexVector = if (columnIndices.height == 1) columnIndices else columnIndices.transposed()

    for (index in columnIndexVector.columnIndices) {
        val colIndex = columnIndices[index]
        val colValue = columnVectors[index]

        for (row in rowIndices) {
            this[row, colIndex] = colValue[row, 0]
        }
    }
    return this
}

fun <T> MutableMatrix<T>.setColumnsFromMatrix(columnIndices: Matrix<Int>, columnMatrix: Matrix<T>): MutableMatrix<T> {
    check(columnIndices.height == 1 || columnIndices.width == 1) {
        "Must be a column or row vector, got <$dimension>"
    }

    val columnIndexVector = if (columnIndices.height == 1) columnIndices else columnIndices.transposed()

    for (index in columnIndexVector.columnIndices) {
        val colIndex = columnIndexVector[index]

        for (row in rowIndices) {
            this[row, colIndex] = columnMatrix[row, index]
        }
    }
    return this
}
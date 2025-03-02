package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.view.viewColumn
import nl.hannahschellekens.grill.view.viewRow

inline fun <T, R> Matrix<T>.fold(initial: R, operation: (accumulator: R, T) -> R): R {
    return elements().fold(initial, operation)
}

inline fun <T, R> Matrix<T>.foldRows(initial: R, operation: (accumulator: R, row: Matrix<T>) -> R): R {
    return rows().fold(initial, operation)
}

inline fun <T, R> Matrix<T>.foldColumns(initial: R, operation: (accumulator: R, column: Matrix<T>) -> R): R {
    return columns().fold(initial, operation)
}

inline fun Matrix<Int>.runningFoldInt(initial: Int, operation: (accumulator: Int, Int) -> Int): Matrix<Int> {
    var accumulator = initial
    return IntMatrix(size + 1, 1, IntArray(size + 1) { index ->
        if (index > 0) {
            accumulator = operation(accumulator, this[index - 1])
        }
        accumulator
    })
}



inline fun Matrix<Double>.runningFold(initial: Double, operation: (accumulator: Double, Double) -> Double): Matrix<Double> {
    var accumulator = initial
    return DoubleMatrix(size + 1, 1, DoubleArray(size + 1) { index ->
        if (index > 0) {
            accumulator = operation(accumulator, this[index - 1])
        }
        accumulator
    })
}

inline fun Matrix<Int>.runningFoldRowsInt(
    initial: MutableMatrix<Int>,
    operation: (accumulator: MutableMatrix<Int>, row: Matrix<Int>) -> MutableMatrix<Int>
): Matrix<Int> {
    check(initial.width == width) { "Initial row width <${initial.width}> does not match matrix width <$width>" }
    check(initial.height == 1) { "Initial row height must be 1, got <${initial.height}>" }

    var accumulator = initial
    return intMatrix(width, height + 1) { row, col ->
        if (row == 0) {
            accumulator[0, col]
        }
        else {
            if (col == 0) {
                accumulator = operation(accumulator, viewRow(row - 1))
            }
            accumulator[0, col]
        }
    }
}

inline fun Matrix<Double>.runningFoldRows(
    initial: MutableMatrix<Double>,
    operation: (accumulator: MutableMatrix<Double>, row: Matrix<Double>) -> MutableMatrix<Double>
): Matrix<Double> {
    check(initial.width == width) { "Initial row width <${initial.width}> does not match matrix width <$width>" }
    check(initial.height == 1) { "Initial row height must be 1, got <${initial.height}>" }

    var accumulator = initial
    return doubleMatrix(width, height + 1) { row, col ->
        if (row == 0) {
            accumulator[0, col]
        }
        else {
            if (col == 0) {
                accumulator = operation(accumulator, viewRow(row - 1))
            }
            accumulator[0, col]
        }
    }
}

inline fun Matrix<Int>.runningFoldColumnsInt(
    initial: MutableMatrix<Int>,
    operation: (accumulator: MutableMatrix<Int>, column: Matrix<Int>) -> MutableMatrix<Int>
): Matrix<Int> {
    check(initial.height == height) {
        "Initial column height <${initial.height}> does not match matrix height <$height>"
    }
    check(initial.width == 1) { "Initial column width must be 1, got <${initial.width}>" }

    var accumulator = initial
    val result = ArrayList<Matrix<Int>>(width + 1)
    result += accumulator.toMatrix()

    for (col in 0 until width) {
        accumulator = operation(accumulator, viewColumn(col))
        result += accumulator.toMatrix()
    }

    return result.toIntMatrixFromColumns()
}

inline fun Matrix<Double>.runningFoldColumns(
    initial: MutableMatrix<Double>,
    operation: (accumulator: MutableMatrix<Double>, column: Matrix<Double>) -> MutableMatrix<Double>
): Matrix<Double> {
    check(initial.height == height) {
        "Initial column height <${initial.height}> does not match matrix height <$height>"
    }
    check(initial.width == 1) { "Initial column width must be 1, got <${initial.width}>" }

    var accumulator = initial
    val result = ArrayList<Matrix<Double>>(width + 1)
    result += accumulator.toMatrix()

    for (col in 0 until width) {
        accumulator = operation(accumulator, viewColumn(col))
        result += accumulator.toMatrix()
    }

    return result.toDoubleMatrixFromColumns()
}
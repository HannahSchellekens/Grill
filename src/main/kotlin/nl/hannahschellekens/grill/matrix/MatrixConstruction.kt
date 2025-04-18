package nl.hannahschellekens.grill.matrix

import nl.hannahschellekens.grill.util.size

fun intMatrixOf(width: Int, height: Int, vararg elements: Int): Matrix<Int> {
    return IntMatrix(width, height, elements)
}

fun intMatrixOf(dimension: Dimension, vararg elements: Int): Matrix<Int> {
    return intMatrixOf(dimension.width, dimension.height, *elements)
}

fun doubleMatrixOf(width: Int, height: Int, vararg elements: Double): Matrix<Double> {
    return DoubleMatrix(width, height, elements)
}

fun doubleMatrixOf(dimension: Dimension, vararg elements: Double): Matrix<Double> {
    return DoubleMatrix(dimension.width, dimension.height, elements)
}

fun squareIntMatrixOf(widthAndHeight: Int, vararg elements: Int): Matrix<Int> {
    return intMatrixOf(widthAndHeight, widthAndHeight, *elements)
}

fun squareDoubleMatrixOf(widthAndHeight: Int, vararg elements: Double): Matrix<Double> {
    return doubleMatrixOf(widthAndHeight, widthAndHeight, *elements)
}

fun intMatrix(width: Int, height: Int, elementProducer: (index: Int) -> Int): Matrix<Int> {
    return IntMatrix(width, height, IntArray(width * height, elementProducer))
}

fun intMatrix(dimension: Dimension, elementProducer: (index: Int) -> Int): Matrix<Int> {
    return intMatrix(dimension.width, dimension.height, elementProducer)
}

fun doubleMatrix(width: Int, height: Int, elementProducer: (index: Int) -> Double): Matrix<Double> {
    return DoubleMatrix(width, height, DoubleArray(width * height, elementProducer))
}

fun doubleMatrix(dimension: Dimension, elementProducer: (index: Int) -> Double): Matrix<Double> {
    return doubleMatrix(dimension.width, dimension.height, elementProducer)
}

inline fun intMatrix(width: Int, height: Int, elementProducer: (row: Int, col: Int) -> Int): Matrix<Int> {
    return IntMatrix(width, height, IntArray(width * height) {
        val row = it / width
        val col = it % width
        elementProducer(row, col)
    })
}

inline fun intMatrix(dimension: Dimension, elementProducer: (row: Int, col: Int) -> Int): Matrix<Int> {
    return intMatrix(dimension.width, dimension.height, elementProducer)
}

inline fun doubleMatrix(width: Int, height: Int, elementProducer: (row: Int, col: Int) -> Double): Matrix<Double> {
    return DoubleMatrix(width, height, DoubleArray(width * height) {
        val row = it / width
        val col = it % width
        elementProducer(row, col)
    })
}

inline fun doubleMatrix(dimension: Dimension, elementProducer: (row: Int, col: Int) -> Double): Matrix<Double> {
    return doubleMatrix(dimension.width, dimension.height, elementProducer)
}

fun squareIntMatrix(widthAndHeight: Int, elementProducer: (index: Int) -> Int): Matrix<Int> {
    return intMatrix(widthAndHeight, widthAndHeight, elementProducer)
}

fun squareDoubleMatrix(widthAndHeight: Int, elementProducer: (index: Int) -> Double): Matrix<Double> {
    return doubleMatrix(widthAndHeight, widthAndHeight, elementProducer)
}

inline fun squareIntMatrix(widthAndHeight: Int, elementProducer: (row: Int, col: Int) -> Int): Matrix<Int> {
    return intMatrix(widthAndHeight, widthAndHeight, elementProducer)
}

inline fun squareDoubleMatrix(widthAndHeight: Int, elementProducer: (row: Int, col: Int) -> Double): Matrix<Double> {
    return doubleMatrix(widthAndHeight, widthAndHeight, elementProducer)
}

fun intIdentity(widthAndHeight: Int): Matrix<Int> = IntIdentity(widthAndHeight)

fun doubleIdentity(widthAndHeight: Int): Matrix<Double> = DoubleIdentity(widthAndHeight)

fun intConstMatrix(width: Int, height: Int, constant: Int): Matrix<Int> = intMatrix(width, height) { _ ->
    constant
}

fun intConstMatrix(dimension: Dimension, constant: Int): Matrix<Int> {
    return intConstMatrix(dimension.width, dimension.height, constant)
}

fun doubleConstMatrix(width: Int, height: Int, constant: Double): Matrix<Double> = doubleMatrix(width, height) { _ ->
    constant
}

fun doubleConstMatrix(dimension: Dimension, constant: Double): Matrix<Double> {
    return doubleConstMatrix(dimension.width, dimension.height, constant)
}

fun intNullMatrix(width: Int, height: Int): Matrix<Int> = intConstMatrix(width, height, 0)

fun intNullMatrix(dimension: Dimension): Matrix<Int> = intNullMatrix(dimension.width, dimension.height)

fun doubleNullMatrix(width: Int, height: Int): Matrix<Double> = doubleConstMatrix(width, height, 0.0)

fun doubleNullMatrix(dimension: Dimension): Matrix<Double> = doubleNullMatrix(dimension.width, dimension.height)

fun <T> constant(width: Int, height: Int, value: T): Matrix<T> = ConstantMatrix(width, height, value)

fun computedIntMatrix(width: Int, height: Int, valueProducer: (row: Int, col: Int) -> Int): Matrix<Int> {
    return ComputedMatrix(width, height, valueProducer)
}

fun computedDoubleMatrix(width: Int, height: Int, valueProducer: (row: Int, col: Int) -> Double): Matrix<Double> {
    return ComputedMatrix(width, height, valueProducer)
}

fun intNullRow(width: Int): MutableMatrix<Int> = IntMatrix(width, 1, IntArray(width))

fun intNullColumn(height: Int): MutableMatrix<Int> = IntMatrix(1, height, IntArray(height))

fun doubleNullRow(width: Int): MutableMatrix<Double> = DoubleMatrix(width, 1, DoubleArray(width))

fun doubleNullColumn(height: Int): MutableMatrix<Double> = DoubleMatrix(1, height, DoubleArray(height))

fun List<Matrix<Int>>.toIntMatrixFromRows(): Matrix<Int> {
    if (isEmpty()) return emptyIntMatrix()
    val height = size
    val width = first().size
    return intMatrix(width, height) { row, col -> this[row][0, col] }
}

fun List<Matrix<Double>>.toDoubleMatrixFromRows(): Matrix<Double> {
    if (isEmpty()) return emptyDoubleMatrix()
    val height = size
    val width = first().size
    return doubleMatrix(width, height) { row, col -> this[row][0, col] }
}

fun List<Matrix<Int>>.toIntMatrixFromColumns(): Matrix<Int> {
    if (isEmpty()) return emptyIntMatrix()
    val width = size
    val height = first().size
    return intMatrix(width, height) { row, col -> this[col][row, 0] }
}

fun List<Matrix<Double>>.toDoubleMatrixFromColumns(): Matrix<Double> {
    if (isEmpty()) return emptyDoubleMatrix()
    val width = size
    val height = first().size
    return doubleMatrix(width, height) { row, col -> this[col][row, 0] }
}
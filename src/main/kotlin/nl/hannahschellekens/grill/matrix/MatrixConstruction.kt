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
        val row = it % width
        val col = it / width
        elementProducer(row, col)
    })
}

inline fun intMatrix(dimension: Dimension, elementProducer: (row: Int, col: Int) -> Int): Matrix<Int> {
    return intMatrix(dimension.width, dimension.height, elementProducer)
}

inline fun doubleMatrix(width: Int, height: Int, elementProducer: (row: Int, col: Int) -> Double): Matrix<Double> {
    return DoubleMatrix(width, height, DoubleArray(width * height) {
        val row = it % width
        val col = it / width
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

fun intRowVectorOf(vararg elements: Int): Matrix<Int> {
    return IntMatrix(elements.size, 1, elements)
}

fun intRowVectorOf(elements: List<Int>): Matrix<Int> {
    return IntMatrix(elements.size, 1, elements.toIntArray())
}

fun List<Int>.toRowIntVector(): Matrix<Int> = intRowVectorOf(this)

fun intColumnVectorOf(vararg elements: Int): Matrix<Int> {
    return IntMatrix(1, elements.size, elements)
}

fun intColumnVectorOf(elements: List<Int>): Matrix<Int> {
    return IntMatrix(1, elements.size, elements.toIntArray())
}

fun List<Int>.toColumnIntVector(): Matrix<Int> = intColumnVectorOf(this)

fun IntRange.toRowVector(): Matrix<Int> = intMatrix(size, 1) { it -> it + start }

fun IntRange.toColumnVector(): Matrix<Int> = intMatrix(1, size) { it -> it + start }

fun doubleRowVectorOf(vararg elements: Double): Matrix<Double> {
    return DoubleMatrix(elements.size, 1, elements)
}

fun doubleRowVectorOf(elements: List<Double>): Matrix<Double> {
    return DoubleMatrix(elements.size, 1, elements.toDoubleArray())
}

fun List<Double>.toRowDoubleVector(): Matrix<Double> = doubleRowVectorOf(this)

fun doubleColumnVectorOf(vararg elements: Double): Matrix<Double> {
    return DoubleMatrix(1, elements.size, elements)
}

fun doubleColumnVectorOf(elements: List<Double>): Matrix<Double> {
    return DoubleMatrix(1, elements.size, elements.toDoubleArray())
}

fun List<Double>.toColumnDoubleVector(): Matrix<Double> = doubleColumnVectorOf(this)

fun constant(width: Int, height: Int, value: Int): Matrix<Int> = ConstantMatrix(width, height, value)

fun constant(width: Int, height: Int, value: Double): Matrix<Double> = ConstantMatrix(width, height, value)

fun computedIntMatrix(width: Int, height: Int, valueProducer: (row: Int, col: Int) -> Int): Matrix<Int> {
    return ComputedMatrix(width, height, valueProducer)
}

fun computedDoubleMatrix(width: Int, height: Int, valueProducer: (row: Int, col: Int) -> Double): Matrix<Double> {
    return ComputedMatrix(width, height, valueProducer)
}
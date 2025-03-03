package nl.hannahschellekens.grill.matrix

import nl.hannahschellekens.grill.util.size

fun Matrix<*>.checkVector() {
    check(width == 1 || height == 1) { "Matrix is not a vector, got <$dimension>" }
}

fun intRowVectorOf(vararg elements: Int): Matrix<Int> {
    return IntMatrix(elements.size, 1, elements)
}

fun intRowVectorOf(elements: List<Int>): Matrix<Int> {
    return IntMatrix(elements.size, 1, elements.toIntArray())
}

inline fun intRowVector(width: Int, valueProducer: (index: Int) -> Int): Matrix<Int> {
    return IntMatrix(width, 1, IntArray(width) { valueProducer(it) })
}

fun List<Int>.toRowIntVector(): Matrix<Int> = intRowVectorOf(this)

fun intColumnVectorOf(vararg elements: Int): Matrix<Int> {
    return IntMatrix(1, elements.size, elements)
}

fun intColumnVectorOf(elements: List<Int>): Matrix<Int> {
    return IntMatrix(1, elements.size, elements.toIntArray())
}

inline fun intColumnVector(height: Int, valueProducer: (index: Int) -> Int): Matrix<Int> {
    return IntMatrix(1, height, IntArray(height) { valueProducer(it) })
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

inline fun doubleRowVector(width: Int, valueProducer: (index: Int) -> Double): Matrix<Double> {
    return DoubleMatrix(width, 1, DoubleArray(width) { valueProducer(it) })
}

fun List<Double>.toRowDoubleVector(): Matrix<Double> = doubleRowVectorOf(this)

fun doubleColumnVectorOf(vararg elements: Double): Matrix<Double> {
    return DoubleMatrix(1, elements.size, elements)
}

fun doubleColumnVectorOf(elements: List<Double>): Matrix<Double> {
    return DoubleMatrix(1, elements.size, elements.toDoubleArray())
}

inline fun doubleColumnVector(height: Int, valueProducer: (index: Int) -> Double): Matrix<Double> {
    return DoubleMatrix(1, height, DoubleArray(height) { valueProducer(it) })
}

fun List<Double>.toColumnDoubleVector(): Matrix<Double> = doubleColumnVectorOf(this)

fun intUnitRowVector(width: Int, oneColumnIndex: Int) = intRowVector(width) { if (it == oneColumnIndex) 1 else 0 }

fun intUnitColumnVector(height: Int, oneRowIndex: Int) = intColumnVector(height) { if (it == oneRowIndex) 1 else 0 }

fun doubleUnitRowVector(width: Int, oneColumnIndex: Int) = doubleRowVector(width) {
    if (it == oneColumnIndex) 1.0 else 0.0
}

fun doubleUnitColumnVector(height: Int, oneRowIndex: Int) = doubleColumnVector(height) {
    if (it == oneRowIndex) 1.0 else 0.0
}
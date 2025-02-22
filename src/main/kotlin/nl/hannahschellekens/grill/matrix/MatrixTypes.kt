package nl.hannahschellekens.grill.matrix

fun Matrix<Int>.toDoubleMatrix(): DoubleMatrix {
    return DoubleMatrix(width, height, DoubleArray(width * height) { index -> this[index].toDouble() })
}

inline fun Matrix<Double>.toIntMatrix(crossinline round: (Double) -> Int = { it.toInt() }): Matrix<Int> {
    return IntMatrix(width, height, IntArray(width * height) { index -> round(this[index]) })
}
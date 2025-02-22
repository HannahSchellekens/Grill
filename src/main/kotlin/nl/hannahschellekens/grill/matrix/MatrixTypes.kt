package nl.hannahschellekens.grill.matrix

fun Matrix<Int>.toDoubleMatrix(): Matrix<Double> {
    return doubleMatrix(width, height) { index -> this[index].toDouble() }
}

inline fun Matrix<Double>.toIntMatrix(crossinline round: (Double) -> Int = { it.toInt() }): Matrix<Int> {
    return intMatrix(width, height) { index -> round(this[index]) }
}
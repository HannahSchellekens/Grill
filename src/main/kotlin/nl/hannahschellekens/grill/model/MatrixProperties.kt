package nl.hannahschellekens.grill.model

val <T> Matrix<T>.size: Int
    get() = width * height

val <T> Matrix<T>.dimension: Dimension
    get() = Dimension(height, width)

val <T> Matrix<T>.isSquare: Boolean
    get() = width == height

fun <T> Matrix<T>.isEmpty(): Boolean = size == 0
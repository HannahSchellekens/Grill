package nl.hannahschellekens.grill.matrix

import nl.hannahschellekens.grill.util.Direction
import nl.hannahschellekens.grill.view.*

operator fun <T> Matrix<T>.get(elementIndex: Int): T {
    val row = elementIndex / width
    val col = elementIndex % width
    return this[row, col]
}

operator fun <T> MutableMatrix<T>.set(elementIndex: Int, value: T) {
    val row = elementIndex / width
    val col = elementIndex % width
    this[row, col] = value
}

operator fun <T> Matrix<T>.get(rowCol: Pair<Int, Int>) = get(rowCol.first, rowCol.second)

fun Matrix<Int>.toMatrix() = IntMatrix(width, height, IntArray(size) {
    this[it]
})

fun Matrix<Double>.toMatrix() = DoubleMatrix(width, height, DoubleArray(size) {
    this[it]
})

fun <T> Matrix<T>.transposed(): View<T> = if (isEmpty()) {
    EmptyView(this)
}
else when (this) {
    is DiagonalView<T> -> {
        DiagonalView(matrix, isTransposed = isTransposed.not())
    }
    is View<T> -> {
        val theWidth = if (isTransposed) height else width
        val theHeight = if (isTransposed) width else height
        MatrixView(matrix, pivot, theWidth, theHeight, isTransposed.not())
    }
    else -> MatrixView(this, 0, width, height, isTransposed = true)
}

fun <T> Matrix<T>.diagonal(): View<T> = DiagonalView(this)

fun <T> Matrix<T>.hstack(right: Matrix<T>, repetitions: Int = 1): View<T> {
    return JoinedView(this, right, direction = Direction.RIGHT, repetitions = repetitions)
}

fun <T> Matrix<T>.vstack(bottom: Matrix<T>, repetitions: Int = 1): View<T> {
    return JoinedView(this, bottom, direction = Direction.DOWN, repetitions = repetitions)
}

fun <T> Matrix<T>.reshape(width: Int, height: Int): View<T> {
    check(size == width * height) {
        "Cannot reshape a matrix of size <$size> to ${Dimension(width, height)}"
    }
    return MatrixView(this, 0, width, height)
}

fun <T> Matrix<T>.reshape(dimension: Dimension) = reshape(dimension.width, dimension.height)

fun <T> Matrix<T>.reshape(widthHeight: Pair<Int, Int>) = reshape(widthHeight.first, widthHeight.second)
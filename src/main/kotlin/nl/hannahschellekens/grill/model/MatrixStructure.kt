package nl.hannahschellekens.grill.model

import nl.hannahschellekens.grill.view.DiagonalView
import nl.hannahschellekens.grill.view.EmptyView
import nl.hannahschellekens.grill.view.MatrixView
import nl.hannahschellekens.grill.view.View

operator fun <T> Matrix<T>.get(elementIndex: Int): T {
    val row = elementIndex / width
    val col = elementIndex % width
    return this[row, col]
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
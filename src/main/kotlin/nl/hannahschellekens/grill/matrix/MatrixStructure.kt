package nl.hannahschellekens.grill.matrix

import nl.hannahschellekens.grill.util.Direction
import nl.hannahschellekens.grill.view.*

operator fun <T> Matrix<T>.get(elementIndex: Int): T {
    val row = elementIndex / width
    val col = elementIndex % width
    return this[row, col]
}

operator fun <T> MutableMatrix<T>.set(elementIndex: Int, value: T): MutableMatrix<T> {
    val row = elementIndex / width
    val col = elementIndex % width
    this[row, col] = value
    return this
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
    is EmptyView<T> -> this
    is SquareView<T> -> {
        isTransposed = true
        this
    }
    is View<T> -> {
        val theWidth = if (isTransposed) height else width
        val theHeight = if (isTransposed) width else height
        MatrixView(matrix, pivot, theWidth, theHeight, isTransposed.not())
    }
    else -> MatrixView(this, 0, width, height, isTransposed = true)
}

fun <T> Matrix<T>.horizontal(): View<T> = if (height > width) transposed() else view()

fun <T> Matrix<T>.vertical(): View<T> = if (width > height) transposed() else view()

fun <T> Matrix<T>.diagonal(): View<T> = DiagonalView(this)

fun <T> Matrix<T>.hstack(right: Matrix<T>, repetitions: Int = 1): View<T> {
    return JoinedView(this, right, direction = Direction.RIGHT, repetitions = repetitions)
}

fun <T> Matrix<T>.vstack(bottom: Matrix<T>, repetitions: Int = 1): View<T> {
    return JoinedView(this, bottom, direction = Direction.DOWN, repetitions = repetitions)
}

fun List<Matrix<Int>>.joinRowIntVectors(): Matrix<Int> {
    if (isEmpty()) return IntMatrix(0, 0, IntArray(0))

    val height = size
    val width = this[0].width
    return IntMatrix(width, height, IntArray(width * height) {
        val row = it / width
        val col = it % width
        this[row][0, col]
    })
}

fun List<Matrix<Int>>.joinColumnIntVectors(): Matrix<Int> {
    if (isEmpty()) return IntMatrix(0, 0, IntArray(0))

    val height = this[0].height
    val width = size
    return IntMatrix(width, height, IntArray(width * height) {
        val row = it / width
        val col = it % width
        this[col][row, 0]
    })
}

fun List<Matrix<Double>>.joinRowDoubleVectors(): Matrix<Double> {
    if (isEmpty()) return DoubleMatrix(0, 0, DoubleArray(0))

    val height = size
    val width = this[0].width
    return DoubleMatrix(width, height, DoubleArray(width * height) {
        val row = it / width
        val col = it % width
        this[row][0, col]
    })
}

fun List<Matrix<Double>>.joinColumnDoubleVectors(): Matrix<Double> {
    if (isEmpty()) return DoubleMatrix(0, 0, DoubleArray(0))

    val height = this[0].height
    val width = size
    return DoubleMatrix(width, height, DoubleArray(width * height) {
        val row = it / width
        val col = it % width
        this[col][row, 0]
    })
}

fun Matrix<Int>.deleteIntRow(row: Int): Matrix<Int> = when {
    isEmpty() -> error("Cannot delete row from empty matrix.")
    height == 1 -> IntMatrix(width, 0, IntArray(0))
    else -> {
        IntMatrix(width, height - 1, IntArray(width * (height - 1)) {
            if (it < width * row) {
                this[it]
            }
            else this[it + width]
        })
    }
}

fun Matrix<Double>.deleteDoubleRow(row: Int): Matrix<Double> = when {
    isEmpty() -> error("Cannot delete row from empty matrix.")
    height == 1 -> DoubleMatrix(width, 0, DoubleArray(0))
    else -> {
        DoubleMatrix(width, height - 1, DoubleArray(width * (height - 1)) {
            if (it < width * row) {
                this[it]
            }
            else this[it + width]
        })
    }
}

fun Matrix<Int>.deleteIntColumn(col: Int): Matrix<Int> = when {
    isEmpty() -> error("Cannot delete row from empty matrix.")
    width == 1 -> IntMatrix(0, height, IntArray(0))
    else -> {
        val newWidth = width - 1
        IntMatrix(newWidth, height, IntArray(newWidth * height) {
            if (it < col) {
                this[it]
            }
            else {
                val skips = (it - col + newWidth) / newWidth
                this[it + skips]
            }
        })
    }
}

fun Matrix<Double>.deleteDoubleColumn(col: Int): Matrix<Double> = when {
    isEmpty() -> error("Cannot delete row from empty matrix.")
    width == 1 -> DoubleMatrix(0, height, DoubleArray(0))
    else -> {
        val newWidth = width - 1
        DoubleMatrix(newWidth, height, DoubleArray(newWidth * height) {
            if (it < col) {
                this[it]
            }
            else {
                val skips = (it - col + newWidth) / newWidth
                this[it + skips]
            }
        })
    }
}

fun <T> Matrix<T>.squareView(padding: T): View<T> = SquareView(this, padding)

fun Matrix<Int>.reshapeInt(width: Int, height: Int): Matrix<Int> {
    check (size == width * height) { "Expected size of $size, got <${width * height}>" }
    return IntMatrix(width, height, IntArray(size) { this[it] })
}

fun Matrix<Double>.reshape(width: Int, height: Int): Matrix<Double> {
    check (size == width * height) { "Expected size of $size, got <${width * height}>" }
    return DoubleMatrix(width, height, DoubleArray(size) { this[it] })
}
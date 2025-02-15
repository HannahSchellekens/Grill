package nl.hannahschellekens.grill.view

import nl.hannahschellekens.grill.matrix.Matrix
import nl.hannahschellekens.grill.matrix.MutableMatrix
import nl.hannahschellekens.grill.matrix.dimension
import nl.hannahschellekens.grill.matrix.isSquare

/**
 * @author Hannah Schellekens
 */
class DiagonalView<T>(
    override val matrix: Matrix<T>,
    override var isTransposed: Boolean = false
) : MutableView<T> {

    init {
        check(matrix.isSquare) { "Matrix must be square, got <${matrix.dimension}>" }
    }

    val initialWidth: Int = 1
    val initialHeight: Int = matrix.height

    override val width: Int = initialWidth
        get() = if (isTransposed) initialHeight else field

    override val height: Int = initialHeight
        get() = if (isTransposed) initialWidth else field

    override val pivot: Int = 0

    private fun rowIndex(row: Int, col: Int) = if (isTransposed) col else row

    override fun get(row: Int, col: Int): T {
        boundsCheck(row, col)

        return matrix[rowIndex(row, col), rowIndex(row, col)]
    }

    override fun set(row: Int, col: Int, value: T) {
        boundsCheck(row, col)

        if (matrix is MutableMatrix) {
            matrix[rowIndex(row, col), rowIndex(row, col)] = value
        }
        else error("Matrix is not of mutable.")
    }

    override fun toString() = printString()
}
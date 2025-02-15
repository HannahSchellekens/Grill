package nl.hannahschellekens.grill.view

import nl.hannahschellekens.grill.matrix.Matrix
import nl.hannahschellekens.grill.matrix.MutableMatrix

/**
 * @author Hannah Schellekens
 */
class MatrixView<T>(
    override val matrix: Matrix<T>,
    override val pivot: Int,
    private val initialWidth: Int,
    private val initialHeight: Int,
    override var isTransposed: Boolean = false
) : MutableView<T> {

    override val width: Int = initialWidth
        get() = if (isTransposed) initialHeight else field

    override val height: Int = initialHeight
        get() = if (isTransposed) initialWidth else field

    private val pivotRow = pivot / matrix.width
    private val pivotCol = pivot % matrix.width

    private fun rowIndex(row: Int, col: Int) = if (isTransposed) col else row
    private fun colIndex(row: Int, col: Int) = if (isTransposed) row else col

    override fun get(row: Int, col: Int): T {
        boundsCheck(row, col)

        return matrix[pivotRow + rowIndex(row, col), pivotCol + colIndex(row, col)]
    }

    override fun set(row: Int, col: Int, value: T) {
        boundsCheck(row, col)

        if (matrix is MutableMatrix) {
            matrix[pivotRow + rowIndex(row, col), pivotCol + colIndex(row, col)] = value
        }
        else error("Matrix is not of mutable.")
    }

    override fun toString() = printString()
}
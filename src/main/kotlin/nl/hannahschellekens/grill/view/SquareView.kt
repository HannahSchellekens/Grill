package nl.hannahschellekens.grill.view

import nl.hannahschellekens.grill.matrix.Matrix
import nl.hannahschellekens.grill.matrix.MutableMatrix
import nl.hannahschellekens.grill.matrix.dimension
import nl.hannahschellekens.grill.matrix.isSquare
import nl.hannahschellekens.grill.view.MutableView
import kotlin.math.max

/**
 * @author Hannah Schellekens
 */
class SquareView<T>(
    override val matrix: Matrix<T>,
    val padding: T,
    override var isTransposed: Boolean = false
) : MutableView<T> {

    val initialWidth: Int = max(matrix.width, matrix.height)
    val initialHeight: Int = max(matrix.width, matrix.height)

    override val width: Int = initialWidth
        get() = if (isTransposed) initialHeight else field

    override val height: Int = initialHeight
        get() = if (isTransposed) initialWidth else field

    override val pivot: Int = 0

    private fun rowIndex(row: Int, col: Int) = if (isTransposed) col else row
    private fun colIndex(row: Int, col: Int) = if (isTransposed) row else col

    override fun get(row: Int, col: Int): T {
        boundsCheck(row, col)

        val rowT = rowIndex(row, col)
        val colT = colIndex(row, col)

        return if (colT >= matrix.width || rowT >= matrix.height) {
            padding
        }
        else matrix[rowT, colT]
    }

    override fun set(row: Int, col: Int, value: T) {
        val rowT = rowIndex(row, col)
        val colT = colIndex(row, col)

        check((colT < matrix.width && rowT < matrix.height)) {
            "Cannot write to padding of padded view given: <($rowT, $colT)>, matrix: <${matrix.dimension}>"
        }

        if (matrix is MutableMatrix) {
            matrix[rowT, colT] = value
        }
        else error("Matrix is not of mutable.")
    }

    override fun toString() = printString()
}
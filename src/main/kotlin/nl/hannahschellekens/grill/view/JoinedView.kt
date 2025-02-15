package nl.hannahschellekens.grill.view

import nl.hannahschellekens.grill.matrix.Matrix
import nl.hannahschellekens.grill.util.Direction

/**
 * @author Hannah Schellekens
 */
class JoinedView<T>(
    val baseMatrix: Matrix<T>,
    val joinedMatrix: Matrix<T>,
    val direction: Direction = Direction.RIGHT,
    val repetitions: Int = 1,
    override val pivot: Int = 0,
    override var isTransposed: Boolean = false
) : MutableView<T> {

    init {
        check(direction.isVertical || baseMatrix.height == joinedMatrix.height) {
            "Matrices do not have equal height, got heights <${baseMatrix.height}> and <${joinedMatrix.height}>"
        }
        check(direction.isHorizontal || baseMatrix.width == joinedMatrix.width) {
            "Matrices do not have equal width, got widths <${baseMatrix.width}> and <${joinedMatrix.width}>"
        }
    }

    override val matrix: Matrix<T> = this

    override val width: Int = when {
        direction.isHorizontal -> baseMatrix.width + joinedMatrix.width * repetitions
        else -> baseMatrix.width
    }

    override val height: Int = when {
        direction.isVertical -> baseMatrix.height + joinedMatrix.height * repetitions
        else -> baseMatrix.height
    }

    private fun rowIndex(row: Int, col: Int) = if (isTransposed) col else row
    private fun colIndex(row: Int, col: Int) = if (isTransposed) row else col

    override fun get(row: Int, col: Int): T {
        boundsCheck(rowIndex(row, col), colIndex(row, col))
        return when {
            direction.isVertical -> verticalGet(rowIndex(row, col), colIndex(row, col))
            else -> horizontalGet(rowIndex(row, col), colIndex(row, col))
        }
    }

    private fun verticalGet(row: Int, col: Int): T {
        val first = if (direction == Direction.UP) joinedMatrix else baseMatrix
        val second = if (direction == Direction.UP) baseMatrix else joinedMatrix

        return if (row >= first.height) {
            second[(row - first.height) % second.height, col]
        }
        else first[row, col]
    }

    private fun horizontalGet(row: Int, col: Int): T {
        val first = if (direction == Direction.LEFT) joinedMatrix else baseMatrix
        val second = if (direction == Direction.LEFT) baseMatrix else joinedMatrix

        return if (col >= first.width) {
            second[row, (col - first.width) % second.width]
        }
        else first[row, col]
    }

    override fun set(row: Int, col: Int, value: T) {
        boundsCheck(rowIndex(row, col), colIndex(row, col))
        when {
            direction.isVertical -> verticalSet(rowIndex(row, col), colIndex(row, col), value)
            else -> horizontalSet(rowIndex(row, col), colIndex(row, col), value)
        }
    }

    private fun verticalSet(row: Int, col: Int, value: T) {
        val first = if (direction == Direction.UP) joinedMatrix else baseMatrix
        val second = if (direction == Direction.UP) baseMatrix else joinedMatrix
    }

    private fun horizontalSet(row: Int, col: Int, value: T) {
        val first = if (direction == Direction.LEFT) joinedMatrix else baseMatrix
        val second = if (direction == Direction.LEFT) baseMatrix else joinedMatrix
    }

    override fun toString() = printString()
}
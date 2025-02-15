package nl.hannahschellekens.grill.model

import nl.hannahschellekens.grill.view.MatrixView

/**
 * @author Hannah Schellekens
 */
class MatrixElementIterator<T>(private val matrix: Matrix<T>): Iterator<T>, Iterable<T> {

    private var elementPointer = -1

    override fun hasNext() = elementPointer + 1 < matrix.size
    override fun next() = matrix[++elementPointer]
    override fun iterator() = this
}

fun <T> Matrix<T>.elements() = MatrixElementIterator(this)

/**
 * @author Hannah Schellekens
 */
class MatrixRowIterator<T>(private val matrix: Matrix<T>): Iterator<Matrix<T>>, Iterable<Matrix<T>> {

    private var rowPointer = -1

    override fun hasNext() = rowPointer + 1 < matrix.height
    override fun next() = MatrixView(
        matrix = matrix,
        pivot = ++rowPointer * matrix.width,
        initialWidth = matrix.width,
        initialHeight = 1
    )
    override fun iterator() = this
}

fun <T> Matrix<T>.rows() = MatrixRowIterator(this)

/**
 * @author Hannah Schellekens
 */
class MatrixColumnIterator<T>(private val matrix: Matrix<T>): Iterator<Matrix<T>>, Iterable<Matrix<T>> {

    private var columnPointer = -1

    override fun hasNext() = columnPointer + 1 < matrix.width
    override fun next() = MatrixView(
        matrix = matrix,
        pivot = ++columnPointer,
        initialWidth = 1,
        initialHeight = matrix.height
    )
    override fun iterator() = this
}

fun <T> Matrix<T>.columns() = MatrixColumnIterator(this)
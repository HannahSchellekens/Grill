package nl.hannahschellekens.grill.matrix

import nl.hannahschellekens.grill.presentation.ListMatrixPrinter
import nl.hannahschellekens.grill.presentation.MatrixPrinter

/**
 * Row-Major Order (RMO) by default.
 *
 * @author Hannah Schellekens
 */
interface Matrix<T> {

    val width: Int
    val height: Int

    operator fun get(row: Int, col: Int): T

    fun defaultPrinter(): MatrixPrinter = ListMatrixPrinter<T>(newLines = true)
    fun printString() = defaultPrinter().toMatrixString(this)

    fun checkBounds(row: Int, col: Int) {
        check(row in 0 until height) { "Row <$row> out of bounds (0 until $height)" }
        check(col in 0 until width) { "Column <$col> out of bounds (0 until $width)" }
    }

    fun checkDimensions(other: Matrix<*>) {
        check(width == other.width && height == other.height) {
            "Dimension mismatch <$dimension> vs other <${other.dimension}>"
        }
    }

    fun contains(element: T): Boolean {
        for (row in 0 until height) {
            for (col in 0 until width) {
                if (get(row, col) == element) {
                    return true
                }
            }
        }
        return false
    }

    fun rowContains(row: Int, element: T): Boolean {
        for (col in 0 until width) {
            if (get(row, col) == element) {
                return true
            }
        }
        return false
    }

    fun columnContains(column: Int, element: T): Boolean {
        for (row in 0 until height) {
            if (get(row, column) == element) {
                return true
            }
        }
        return false
    }
}

/**
 * Row-Major Order (RMO) by default.
 *
 * @author Hannah Schellekens
 */
interface MutableMatrix<T> : Matrix<T> {

    operator fun set(row: Int, col: Int, value: T)

    fun swapRows(row1: Int, row2: Int) {
        for (col in columnIndices) {
            val temp = this[row1, col]
            this[row1, col] = this[row2, col]
            this[row2, col] = temp
        }
    }

    fun swapColumns(col1: Int, col2: Int) {
        for (row in rowIndices) {
            val temp = this[row, col1]
            this[row, col1] = this[row, col2]
            this[row, col2] = temp
        }
    }
}

val <T> Matrix<T>.mutable: MutableMatrix<T>?
    get() = this as? MutableMatrix<T>
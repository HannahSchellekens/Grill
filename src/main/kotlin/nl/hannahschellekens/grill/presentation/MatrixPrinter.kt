package nl.hannahschellekens.grill.presentation

import nl.hannahschellekens.grill.matrix.Matrix

/**
 * @author Hannah Schellekens
 */
interface MatrixPrinter {

    fun <T> toMatrixString(
        matrix: Matrix<T>,
        toString: (T) -> String = {
            it.toString()
        }): String
}
package nl.hannahschellekens.grill.presentation

import nl.hannahschellekens.grill.matrix.Matrix
import nl.hannahschellekens.grill.matrix.elements
import nl.hannahschellekens.grill.matrix.isEmpty
import nl.hannahschellekens.grill.util.range
import nl.hannahschellekens.grill.util.startGroup

/**
 * `RMO[[1,2,3],[4,5,6],[7,8,9]]`
 *
 * @author Hannah Schellekens
 */
open class ListMatrixPrinter<T>(
    private val newLines: Boolean = false,
    private val equalWidthFormatting: Boolean = true,
) : MatrixPrinter {

    override fun <T> toMatrixString(
        matrix: Matrix<T>,
        toString: (T) -> String
    ) = if (matrix.isEmpty()) {
        "[]"
    }
    else buildString {
        val elements = matrix.elements().map { toString(it) }
        val maxLength = elements.maxOf { it.length }
        val format = "%${maxLength}s"

        startGroup {
            for (row in matrix.height.range) {
                startGroup {
                    val rowString = matrix.width.range.joinToString(", ") { col ->
                        format.format(elements[row * matrix.width + col].replace(',', '.'))
                    }
                    append(rowString)
                }
                if (newLines && row != matrix.height - 1) {
                    append(",")
                    append("\n ")
                }
            }
        }
    }
}
package nl.hannahschellekens.grill.algorithm

import nl.hannahschellekens.grill.matrix.MutableMatrix
import nl.hannahschellekens.grill.matrix.swapToNonZero
import nl.hannahschellekens.grill.operations.addRowModify
import nl.hannahschellekens.grill.operations.scalarRowModify
import nl.hannahschellekens.grill.util.DEFAULT_EPSILON
import nl.hannahschellekens.grill.util.approxEquals
import nl.hannahschellekens.grill.util.approxZero

/**
 * @author Hannah Schellekens
 */
object GaussianElimination {

    fun rowReduce(matrix: MutableMatrix<Double>, epsilon: Double = DEFAULT_EPSILON): MutableMatrix<Double> {
        var pivotRow = 0
        var pivotCol = 0

        while (pivotRow < matrix.height && pivotCol < matrix.width) {
            // Pull a nonzero pivot to this row.
            matrix.swapToNonZero(pivotRow, pivotCol, epsilon)
            val pivot = matrix[pivotRow, pivotCol]

            // Find a pivot in the row that is not zero.
            if (pivot.approxZero(epsilon)) {
                pivotCol++
                continue
            }

            // Scale row so pivot becomes 1.0
            if (pivot.approxEquals(1.0, epsilon).not()) {
                matrix.scalarRowModify(pivotRow, 1.0 / pivot)
            }

            // Eliminate other rows.
            for (row in 0 until matrix.height) {
                if (row == pivotRow) continue

                val element = matrix[row, pivotCol]
                if (element.approxZero(epsilon)) continue
                matrix.addRowModify(row, pivotRow, -element)
            }

            // Move to next pivot.
            pivotRow++
            pivotCol++
        }

        return matrix
    }
}
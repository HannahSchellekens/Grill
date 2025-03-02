package nl.hannahschellekens.grill.matrix

import nl.hannahschellekens.grill.util.DEFAULT_EPSILON
import nl.hannahschellekens.grill.util.approxEquals

/**
 * @author Hannah Schellekens
 */
open class DoubleMatrix(
    override val width: Int,
    override val height: Int,
    // RMO by default - modify with Views.
    private val elements: DoubleArray,
    val epsilon: Double = DEFAULT_EPSILON
) : MutableMatrix<Double> {

    override fun set(row: Int, col: Int, value: Double) {
        checkBounds(row, col)

        elements[row * width + col] = value
    }

    override fun get(row: Int, col: Int): Double {
        checkBounds(row, col)

        return elements[row * width + col]
    }

    override fun contains(element: Double): Boolean {
        for (row in 0 until height) {
            for (col in 0 until width) {
                if (get(row, col).approxEquals(element, epsilon)) {
                    return true
                }
            }
        }
        return false
    }

    override fun rowContains(row: Int, element: Double): Boolean {
        for (col in 0 until width) {
            if (get(row, col).approxEquals(element, epsilon)) {
                return true
            }
        }
        return false
    }

    override fun columnContains(column: Int, element: Double): Boolean {
        for (row in 0 until height) {
            if (get(row, column).approxEquals(element, epsilon)) {
                return true
            }
        }
        return false
    }

    override fun toString() = defaultPrinter().toMatrixString(this) {
        "%#.4f".format(it)
    }
}
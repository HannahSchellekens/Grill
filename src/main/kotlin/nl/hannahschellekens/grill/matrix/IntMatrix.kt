package nl.hannahschellekens.grill.matrix

import nl.hannahschellekens.grill.nl.hannahschellekens.grill.operations.SortableByElement

/**
 * @author Hannah Schellekens
 */
open class IntMatrix(
    override val width: Int,
    override val height: Int,
    // RMO by default - modify with Views.
    private val elements: IntArray
) : MutableMatrix<Int>, SortableByElement<Int> {

    override fun set(row: Int, col: Int, value: Int) {
        checkBounds(row, col)

        elements[row * width + col] = value
    }

    override fun get(row: Int, col: Int): Int {
        checkBounds(row, col)

        return elements[row * width + col]
    }

    override fun sort() {
        elements.sort()
    }

    override fun toString() = printString()
}
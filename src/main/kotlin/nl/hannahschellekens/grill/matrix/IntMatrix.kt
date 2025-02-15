package nl.hannahschellekens.grill.matrix

/**
 * @author Hannah Schellekens
 */
open class IntMatrix(
    override val width: Int,
    override val height: Int,
    // RMO by default - modify with Views.
    private val elements: IntArray
) : MutableMatrix<Int> {

    override fun set(row: Int, col: Int, value: Int) {
        elements[row * width + col] = value
    }

    override fun get(row: Int, col: Int): Int {
        return elements[row * width + col]
    }

    override fun toString() = printString()
}
package nl.hannahschellekens.grill.view

import nl.hannahschellekens.grill.matrix.Matrix

/**
 * @author Hannah Schellekens
 */
class EmptyView<T>(
    override val matrix: Matrix<T>
) : View<T> {

    override val width: Int = 0
    override val height: Int = 0
    override val pivot: Int = -1
    override var isTransposed: Boolean = true

    override fun get(row: Int, col: Int): T {
        error("Cannot get an element from a 0x0 matrix.")
    }

    override fun toString() = printString()
}
package nl.hannahschellekens.grill.matrix

/**
 * @author Hannah Schellekens
 */
class ConstantMatrix<T>(
    override val width: Int,
    override val height: Int,
    val constant: T
) : Matrix<T> {

    override fun get(row: Int, col: Int) = constant

    override fun toString() = printString()
}
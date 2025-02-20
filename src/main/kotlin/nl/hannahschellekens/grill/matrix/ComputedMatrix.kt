package nl.hannahschellekens.grill.matrix

/**
 * @author Hannah Schellekens
 */
class ComputedMatrix<T>(
    override val width: Int,
    override val height: Int,
    val valueProducer: (row: Int, col: Int) -> T
) : Matrix<T> {

    override fun get(row: Int, col: Int) = valueProducer(row, col)

    override fun toString() = printString()
}
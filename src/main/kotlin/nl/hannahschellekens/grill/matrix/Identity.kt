package nl.hannahschellekens.grill.matrix

/**
 * @author Hannah Schellekens
 */
class IntIdentity(dimension: Int) : Matrix<Int> {

    override val width: Int = dimension
    override val height: Int = dimension

    override fun get(row: Int, col: Int) = if (row == col) 1 else 0

    override fun toString() = printString()
}

/**
 * @author Hannah Schellekens
 */
class DoubleIdentity(dimension: Int) : Matrix<Double> {

    override val width: Int = dimension
    override val height: Int = dimension

    override fun get(row: Int, col: Int) = if (row == col) 1.0 else 0.0

    override fun toString() = printString()
}
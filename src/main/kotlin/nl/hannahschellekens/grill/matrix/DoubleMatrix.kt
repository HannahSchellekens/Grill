package nl.hannahschellekens.grill.matrix

/**
 * @author Hannah Schellekens
 */
open class DoubleMatrix(
    override val width: Int,
    override val height: Int,
    // RMO by default - modify with Views.
    private val elements: DoubleArray,
    val epsilon: Double = 10e-12
) : MutableMatrix<Double> {

    override fun set(row: Int, col: Int, value: Double) {
        boundsCheck(row, col)

        elements[row * width + col] = value
    }

    override fun get(row: Int, col: Int): Double {
        boundsCheck(row, col)

        return elements[row * width + col]
    }

    override fun toString() = defaultPrinter().toMatrixString(this) {
        "%#.4f".format(it)
    }
}
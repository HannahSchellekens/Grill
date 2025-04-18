package nl.hannahschellekens.grill.matrix

/**
 * @author Hannah Schellekens
 */
data class Dimension(val height: Int, val width: Int) {

    override fun toString(): String {
        return "Dim($height×$width)"
    }
}
package nl.hannahschellekens.grill.util

/**
 * @author Hannah Schellekens
 */
enum class Direction(val isHorizontal: Boolean) {

    TOP(false),
    RIGHT(true),
    BOTTOM(false),
    LEFT(true),
    ;

    val isVertical: Boolean = isHorizontal.not()
}
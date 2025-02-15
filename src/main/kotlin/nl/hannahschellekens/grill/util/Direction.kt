package nl.hannahschellekens.grill.util

/**
 * @author Hannah Schellekens
 */
enum class Direction(val isHorizontal: Boolean) {

    UP(false),
    RIGHT(true),
    DOWN(false),
    LEFT(true),
    ;

    val isVertical: Boolean = isHorizontal.not()
}
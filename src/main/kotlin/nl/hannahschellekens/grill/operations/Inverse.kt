package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.util.DEFAULT_EPSILON
import nl.hannahschellekens.grill.view.view
import nl.hannahschellekens.grill.view.viewColumns

fun Matrix<Double>.inverse(epsilon: Double = DEFAULT_EPSILON): Matrix<Double>? {
    check(isSquare) { "Matrix is not square, got <$dimension>" }

    val augmented = hstack(doubleIdentity(width)).toMatrix() /* Copy to allow mutations without altering original */
    augmented.rowReduce(epsilon)

    // Check if inverse exists.
    val id = augmented.view(0 until width, 0 until height)
    if (id.isIdentity(epsilon).not()) return null

    // Return copy of inverse.
    return augmented.viewColumns(width).toMatrix()
}
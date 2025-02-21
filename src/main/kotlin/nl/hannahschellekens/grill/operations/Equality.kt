package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.nl.hannahschellekens.grill.util.DEFAULT_EPSILON
import nl.hannahschellekens.grill.nl.hannahschellekens.grill.util.approxEquals

fun Matrix<Int>.contentEqualsInt(other: Matrix<Int>): Boolean {
    if (width != other.width || height != other.height) return false

    for (i in 0 until size) {
        if (this[i] != other[i]) {
            return false
        }
    }
    return true
}

fun Matrix<Double>.contentEquals(other: Matrix<Double>, epsilon: Double = DEFAULT_EPSILON): Boolean {
    if (width != other.width || height != other.height) return false

    for (i in 0 until size) {
        if (this[i].approxEquals(other[i], epsilon).not()) {
            return false
        }
    }
    return true
}
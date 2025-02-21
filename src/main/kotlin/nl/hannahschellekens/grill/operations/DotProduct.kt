package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*

fun Matrix<Int>.dotProductInt(other: Matrix<Int>): Int {
    check(width == other.width && height == other.height) {
        "Dimensions do not match, receiving: <$dimension>, parameter: <${other.dimension}>"
    }
    check(width == 1 || height == 1) {
        "Receiving width or height must be 1, got <$dimension>"
    }
    check(other.width == 1 || height == 1) {
        "Parameter width or height must be 1, got <$dimension>"
    }

    var sum = 0
    for (i in 0 until size) {
        sum += (this[i] * other[i])
    }
    return sum
}

fun Matrix<Double>.dotProduct(other: Matrix<Double>): Double {
    check(width == other.width && height == other.height) {
        "Dimensions do not match, receiving: <$dimension>, parameter: <${other.dimension}>"
    }
    check(width == 1 || height == 1) {
        "Receiving width or height must be 1, got <$dimension>"
    }
    check(other.width == 1 || height == 1) {
        "Parameter width or height must be 1, got <$dimension>"
    }

    var sum = 0.0
    for (i in 0 until size) {
        sum += (this[i] * other[i])
    }
    return sum
}
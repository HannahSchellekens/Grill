package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*

fun Matrix<Int>.crossProductInt(other: Matrix<Int>): Matrix<Int> {
    check((width == 1 && height == 3) || (width == 3 && height == 1)) {
        "Receiving vector is not 1x3 or 3x1, got <$dimension>"
    }
    check((other.width == 1 && other.height == 3) || (other.width == 3 && other.height == 1)) {
        "Parameter vector is not 1x3 or 3x1, got <${other.dimension}>"
    }

    val a = vertical()
    val b = other.vertical()

    return intColumnVectorOf(
        a[1] * b[2] - a[2] * b[1],
        a[2] * b[0] - a[0] * b[2],
        a[0] * b[1] - a[1] * b[0]
    )
}

fun Matrix<Double>.crossProduct(other: Matrix<Double>): Matrix<Double> {
    check((width == 1 && height == 3) || (width == 3 && height == 1)) {
        "Receiving vector is not 1x3 or 3x1, got <$dimension>"
    }
    check((other.width == 1 && other.height == 3) || (other.width == 3 && other.height == 1)) {
        "Parameter vector is not 1x3 or 3x1, got <${other.dimension}>"
    }

    val a = vertical()
    val b = other.vertical()

    return doubleColumnVectorOf(
        a[1] * b[2] - a[2] * b[1],
        a[2] * b[0] - a[0] * b[2],
        a[0] * b[1] - a[1] * b[0]
    )
}
package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.view.viewColumn
import nl.hannahschellekens.grill.view.viewRow

fun Matrix<Int>.multiplyInt(other: Matrix<Int>): Matrix<Int> {
    check(width == other.height) {
        "Incorrect dimensions for multiplication, got receiving: <$dimension> and parameter: <${other.dimension}>"
    }

    return intMatrix(height, height) { row, col ->
        viewRow(row).dotProductInt(other.viewColumn(col).transposed())
    }
}

fun Matrix<Double>.multiply(other: Matrix<Double>): Matrix<Double> {
    check(width == other.height) {
        "Incorrect dimensions for multiplication, got receiving: <$dimension> and parameter: <${other.dimension}>"
    }

    return doubleMatrix(height, height) { row, col ->
        viewRow(row).dotProduct(other.viewColumn(col).transposed())
    }
}

operator fun Matrix<Double>.times(other: Matrix<Double>): Matrix<Double> = multiply(other)
package nl.hannahschellekens.grill.nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.operations.norm
import nl.hannahschellekens.grill.operations.subtract
import kotlin.math.abs
import kotlin.math.sqrt

fun Matrix<Double>.distance(other: Matrix<Double>, norm: (Matrix<Double>) -> Double = { norm() }): Double {
    checkVector()
    return norm(subtract(other))
}
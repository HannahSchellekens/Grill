package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.algorithm.GaussianElimination
import nl.hannahschellekens.grill.matrix.MutableMatrix
import nl.hannahschellekens.grill.util.DEFAULT_EPSILON

fun MutableMatrix<Double>.rowReduce(epsilon: Double = DEFAULT_EPSILON): MutableMatrix<Double> {
    return GaussianElimination.rowReduce(this, epsilon)
}
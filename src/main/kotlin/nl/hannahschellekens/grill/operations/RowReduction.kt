package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.algorithm.GaussianElimination
import nl.hannahschellekens.grill.matrix.MutableMatrix

fun MutableMatrix<Double>.rowReduce(): MutableMatrix<Double> {
    return GaussianElimination.rowReduce(this)
}
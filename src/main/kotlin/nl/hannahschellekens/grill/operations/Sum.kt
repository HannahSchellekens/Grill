package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.algorithm.JobAssignment
import nl.hannahschellekens.grill.matrix.Matrix
import nl.hannahschellekens.grill.matrix.get
import nl.hannahschellekens.grill.matrix.size

fun Matrix<Double>.sum(): Double {
    var sum = 0.0
    for (i in 0 until size) {
        sum += this[i]
    }
    return sum
}

fun Matrix<Int>.sumInt(): Int {
    var sum = 0
    for (i in 0 until size) {
        sum += this[i]
    }
    return sum
}

fun Matrix<Double>.sum(jobs: JobAssignment): Double {
    var sum = 0.0
    for ((worker, job) in jobs) {
        sum += this[worker, job]
    }
    return sum
}

fun Matrix<Int>.sumInt(jobs: JobAssignment): Int {
    var sum = 0
    for ((worker, job) in jobs) {
        sum += this[worker, job]
    }
    return sum
}
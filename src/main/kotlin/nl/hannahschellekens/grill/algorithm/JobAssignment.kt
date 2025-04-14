package nl.hannahschellekens.grill.algorithm

import nl.hannahschellekens.grill.matrix.Matrix
import nl.hannahschellekens.grill.matrix.toDoubleMatrix
import nl.hannahschellekens.grill.util.DEFAULT_EPSILON

typealias JobAssignment = Map<Int, Int>

/**
 * Assigns workers `i` to jobs `j`.
 *
 * @author Hannah Schellekens
 */
interface JobAssignmentAlgorithm {

    /**
     * Assigns workers to jobs.
     *
     * @return A worker-job assignment mapping each worker index (row) -> job index (col).
     */
    fun assignJobs(): JobAssignment
}

fun Matrix<Double>.assignJobsMinimize(
    epsilon: Double = DEFAULT_EPSILON,
    algorithm: JobAssignmentAlgorithm = HungarianAlgorithm(this, epsilon = epsilon),
): JobAssignment {
    return algorithm.assignJobs()
}

fun Matrix<Int>.assignJobsMinimizeInt(
    epsilon: Double = DEFAULT_EPSILON,
    algorithm: JobAssignmentAlgorithm = HungarianAlgorithm(toDoubleMatrix(), epsilon = epsilon),
): JobAssignment {
    return algorithm.assignJobs()
}

fun Matrix<Double>.assignJobsMaximize(
    epsilon: Double = DEFAULT_EPSILON,
    algorithm: JobAssignmentAlgorithm = HungarianAlgorithm(
        this,
        epsilon = epsilon,
        goal = HungarianAlgorithm.Goal.MAXIMIZE
    ),
): JobAssignment {
    return algorithm.assignJobs()
}

fun Matrix<Int>.assignJobsMaximizeInt(
    epsilon: Double = DEFAULT_EPSILON,
    algorithm: JobAssignmentAlgorithm = HungarianAlgorithm(
        toDoubleMatrix(),
        epsilon = epsilon,
        goal = HungarianAlgorithm.Goal.MAXIMIZE
    ),
): JobAssignment {
    return algorithm.assignJobs()
}
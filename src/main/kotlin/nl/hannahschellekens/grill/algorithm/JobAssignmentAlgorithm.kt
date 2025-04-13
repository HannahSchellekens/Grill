package nl.hannahschellekens.grill.algorithm

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
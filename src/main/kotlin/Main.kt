package nl.hannahschellekens.grill

import nl.hannahschellekens.grill.algorithm.HungarianAlgorithm
import nl.hannahschellekens.grill.graph.AdjacencyList
import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.operations.subtractColumnMinima
import nl.hannahschellekens.grill.operations.subtractRowMinima
import kotlin.random.Random
import kotlin.system.measureNanoTime

/**
 * @author Hannah Schellekens
 */
fun main() {
//    val test = doubleMatrixOf(5, 5,
//        30.41, 34.72, 28.89, 27.16, 99.99,
//        32.78, 37.19, 29.83, 27.65, 99.99,
//        34.72, 37.22, 33.61, 29.61, 99.99,
//        33.47, 38.49, 33.62, 28.95, 99.99,
//        34.92, 39.32, 33.50, 30.04, 99.99
//    )
//    val algo = HungarianAlgorithm(test)
//    val assignment = algo.assignJobs()
//
//    println("\nOptimal assignment:")
//    assignment.forEach { (worker, task) ->
//        println("Worker $worker -> Task $task")
//    }

//    val test2 = intMatrixOf(10, 10,
//        48, 51, 27, 33, 66, 12, 82, 36, 27, 22,
//        25, 29, 45, 81, 30, 8, 69, 10, 94, 85,
//        63, 46, 40, 67, 20, 26, 74, 80, 87, 85,
//        93, 51, 74, 16, 69, 76, 41, 49, 42, 38,
//        52, 19, 48, 87, 6, 92, 70, 75, 62, 87,
//        55, 81, 57, 7, 85, 54, 5, 6, 90, 75,
//        49, 82, 2, 65, 28, 33, 54, 25, 63, 3,
//        39, 3, 1, 79, 10, 59, 99, 32, 76, 77,
//        61, 23, 63, 84, 87, 84, 76, 82, 94, 88,
//        95, 4, 16, 4, 73, 76, 88, 31, 58, 38,
//    ).toDoubleMatrix()
//
//    val algo2 = HungarianAlgorithm(test2)
//    val assignment2 = algo2.assignJobs()
//
//    println("\nOptimal assignment 2:")
//    assignment2.forEach { (worker, task) ->
//        println("Worker $worker -> Task $task")
//    }

    val maxValue = 10000.0
    val rand = Random(123)
    println("n rows\ttime (ns)")
    repeat(100) { n ->
        val M = doubleMatrix(n + 1, n + 1) { _, _ -> maxValue * rand.nextDouble() }

        val timed = measureNanoTime {
            if (n + 1 == 14) {
                HungarianAlgorithm(M).assignJobs()
            }
        }
        //println("$n\t$timed")
    }
}
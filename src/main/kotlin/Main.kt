package nl.hannahschellekens.grill

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.operations.max
import nl.hannahschellekens.grill.operations.min
import nl.hannahschellekens.grill.view.*

/**
 * @author Hannah Schellekens
 */
fun main() {

    val A = intMatrix(4, 6) { i -> i + 1 }
    println("[A] Dim: ${A.dimension}, Size: ${A.size}, Max: ${A.max()}, Min: ${A.min()}")
    println(A)
    println()

    println(A.viewRow(2))
    println(A.viewColumn(2)) // Dit gaat fout, hij pakt niet ieder element uit de kolom, maar
    println(A[0..5, 2..3])       // sequentiele elementen...
    A.columns().forEach { println(it) } // Kijk maar, helemaal wak. Werkte eerste wel.
    println()

    val reshaped = A.reshape(6, 4)
    println("[reshaped] Dim: ${reshaped.dimension}, Size: ${reshaped.size}, Max: ${reshaped.max()}, Min: ${reshaped.min()}")
    println(reshaped)
    println()

    val vectors = (1..3).map {
        (1..5).shuffled().toRowIntVector().reshape(1, 5)
    }
    println(vectors)
    println(vectors.joinColumnIntVectors())
}
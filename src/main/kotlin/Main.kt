package nl.hannahschellekens.grill

import nl.hannahschellekens.grill.matrix.*
import nl.hannahschellekens.grill.operations.max
import nl.hannahschellekens.grill.operations.min
import nl.hannahschellekens.grill.view.JoinedView

/**
 * @author Hannah Schellekens
 */
fun main() {

    val A = IntMatrix(3, 3, IntArray(9) { it + 1 })
    println("[A] Dim: ${A.dimension}, Size: ${A.size}, Max: ${A.max()}, Min: ${A.min()}")
    println(A)
    println()

    val B = IntMatrix(3, 3, IntArray(9) { it + 10 })
    println("[B] Dim: ${B.dimension}, Size: ${B.size}, Max: ${B.max()}, Min: ${B.min()}")
    println(B)
    println()

    val AB = A.vstack(B)
    println("[AB] Dim: ${AB.dimension}, Size: ${AB.size}, Max: ${AB.max()}, Min: ${AB.min()}")
    println(AB)
    println()

    val ABAB = AB.vstack(AB)
    println("[ABAB] Dim: ${ABAB.dimension}, Size: ${ABAB.size}, Max: ${ABAB.max()}, Min: ${ABAB.min()}")
    println(ABAB)
    println()

    val ABB = JoinedView(A, B, repetitions = 2)
    println("[ABB] Dim: ${ABB.dimension}, Size: ${ABB.size}, Max: ${ABB.max()}, Min: ${ABB.min()}")
    println(ABB)
    println()
}
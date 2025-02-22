package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.*
import java.util.Random

fun <T> MutableMatrix<T>.shuffleElements(random: Random = Random()): MutableMatrix<T> {
    // Fisher-Yates Shuffle
    for (i in (size - 1) downTo 1) {
        val j = random.nextInt(i + 1)

        val first = this[i]
        this[i] = this[j]
        this[j] = first
    }
    return this
}

fun <T> MutableMatrix<T>.shuffleRows(random: Random = Random()): MutableMatrix<T> {
    // Fisher-Yates Shuffle
    for (i in (height - 1) downTo 1) {
        val j = random.nextInt(i + 1)
        swapRows(j, i)
    }
    return this
}

fun <T> MutableMatrix<T>.shuffleColumns(random: Random = Random()): MutableMatrix<T> {
    // Fisher-Yates Shuffle
    for (i in (width - 1) downTo 1) {
        val j = random.nextInt(i + 1)
        swapColumns(j, i)
    }
    return this
}
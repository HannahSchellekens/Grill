package nl.hannahschellekens.grill.util

/**
 * Creates a tuple of type [Pair] from this and [that].
 * Makes sure that the smallest element is the first element in the pair.
 */
public infix fun <T : Comparable<T>> T.toOrdered(that: T): Pair<T, T> {
    return if (this < that) {
        this to that
    }
    else that to this
}
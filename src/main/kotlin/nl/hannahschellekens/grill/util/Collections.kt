package nl.hannahschellekens.grill.util

fun <T> Iterable<T>.toPair(): Pair<T, T> {
    var first: T? = null
    var second: T? = null
    forEachIndexed { index, t ->
        when (index) {
            0 -> first = t
            1 -> second = t
            else -> return@forEachIndexed
        }
    }
    if (first == null || second == null) {
        error("Iterable contains fewer than 2 elements!")
    }
    return first!! to second!!
}
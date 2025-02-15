package nl.hannahschellekens.grill.util

/**
 * Exclusive integer range `{0, 1, ..., n - 1}`
 */
val Int.range: IntRange
    get() = 0 until this

val IntRange.size: Int
    get() = endInclusive - first + 1
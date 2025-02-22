package nl.hannahschellekens.grill.util

import kotlin.math.abs

const val DEFAULT_EPSILON: Double = 10E-9

fun Double.approxEquals(other: Double, epsilon: Double = DEFAULT_EPSILON): Boolean {
    return abs(this - other) < epsilon
}

fun Double.approxZero(epsilon: Double = DEFAULT_EPSILON): Boolean {
    return abs(this) <= epsilon
}
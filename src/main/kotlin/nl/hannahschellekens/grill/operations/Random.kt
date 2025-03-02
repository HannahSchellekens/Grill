package nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.Matrix
import nl.hannahschellekens.grill.matrix.get
import nl.hannahschellekens.grill.matrix.size
import nl.hannahschellekens.grill.view.View
import nl.hannahschellekens.grill.view.viewColumn
import nl.hannahschellekens.grill.view.viewRow

typealias JavaRandom = java.util.Random

fun <T> Matrix<T>.randomElement(random: JavaRandom = JavaRandom()): T = this[random.nextInt(size)]

fun <T> Matrix<T>.randomRow(random: JavaRandom = JavaRandom()): View<T> = viewRow(random.nextInt(height))

fun <T> Matrix<T>.randomColumn(random: JavaRandom = JavaRandom()): View<T> = viewColumn(random.nextInt(width))
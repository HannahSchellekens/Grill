package nl.hannahschellekens.grill.model

import nl.hannahschellekens.grill.presentation.ListMatrixPrinter
import nl.hannahschellekens.grill.presentation.MatrixPrinter
import nl.hannahschellekens.grill.view.MutableView
import nl.hannahschellekens.grill.view.View

/**
 * Row-Major Order (RMO) by default.
 *
 * @author Hannah Schellekens
 */
interface Matrix<T> {

    val width: Int
    val height: Int

    operator fun get(row: Int, col: Int): T

    fun defaultPrinter(): MatrixPrinter = ListMatrixPrinter<T>(newLines = true)
    fun printString() = defaultPrinter().toMatrixString(this)
}

/**
 * Row-Major Order (RMO) by default.
 *
 * @author Hannah Schellekens
 */
interface MutableMatrix<T> : Matrix<T> {

    operator fun set(row: Int, col: Int, value: T)
}

val <T> Matrix<T>.mutable: MutableMatrix<T>?
    get() = this as? MutableMatrix<T>
package nl.hannahschellekens.grill.view

import nl.hannahschellekens.grill.model.Matrix
import nl.hannahschellekens.grill.model.MutableMatrix

/**
 * @author Hannah Schellekens
 */
interface View<T> : Matrix<T> {

    val pivot: Int
    val matrix: Matrix<T>
    var isTransposed: Boolean
}

fun <T> Matrix<T>.view(): View<T> = MatrixView(this, 0, width, height)

/**
 * @author Hannah Schellekens
 */
interface MutableView<T> : View<T>, MutableMatrix<T> {
}

val <T> View<T>.mutable: MutableView<T>?
    get() = this as? MutableView<T>
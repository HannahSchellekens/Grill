package nl.hannahschellekens.grill.nl.hannahschellekens.grill.operations

import nl.hannahschellekens.grill.matrix.MutableMatrix

/**
 * @author Hannah Schellekens
 */
interface SortableByElement<T : Comparable<T>> {

    fun sort()
}

fun <T : Comparable<T>> MutableMatrix<T>.sortElements() {
    if (this is SortableByElement<*>) {
        sort()
    }
    else error("Sort is unsupported by matrix type <${javaClass.name}>")
}
package nl.hannahschellekens.grill.util

public inline fun StringBuilder.startGroup(
    prefix: String = "[",
    suffix: String = "]",
    builderAction: StringBuilder.() -> Unit
): Unit {
    append(prefix)
    builderAction()
    append(suffix)
}

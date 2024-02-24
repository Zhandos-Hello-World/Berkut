package kz.cicada.berkut.lib.core.ui.extensions

fun Int.isEven(): Boolean = this % 2 == 0

fun Int.isOdd(): Boolean = this % 2 != 0

val Int?.isZero: Boolean
    get() = this == 0
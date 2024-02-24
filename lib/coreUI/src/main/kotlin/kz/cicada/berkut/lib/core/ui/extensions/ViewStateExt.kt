package kz.cicada.berkut.lib.core.ui.extensions

import kz.cicada.berkut.lib.core.ui.base.ViewState

val <T : Any> ViewState<T>.dataOrNull: T?
    get() = (this as? ViewState.Data)?.data
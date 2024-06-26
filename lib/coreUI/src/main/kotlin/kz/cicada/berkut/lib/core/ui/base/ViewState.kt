package kz.cicada.berkut.lib.core.ui.base

sealed class ViewState<out T : Any> {
    data class Loading(val type: LoadingType) : ViewState<Nothing>()
    data class Data<out T : Any>(val data: T) : ViewState<T>()
    data class Error<out T : Any>(
        val error: Throwable,
        val errorData: T? = null,
    ) : ViewState<T>()
}

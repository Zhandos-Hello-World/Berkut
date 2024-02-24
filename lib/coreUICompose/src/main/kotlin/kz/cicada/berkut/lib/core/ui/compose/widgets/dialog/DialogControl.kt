package kz.cicada.berkut.lib.core.ui.compose.widgets.dialog

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DialogControl<T : Any, R : Any> {

    sealed class State<out T : Any> {
        data class Shown<T : Any>(val data: T, val forResult: Boolean) : State<T>()
        object Hidden : State<Nothing>()
    }

    private val mutableStateFlow = MutableStateFlow<State<T>>(State.Hidden)
    private val resultChannel = Channel<R?>(Channel.RENDEZVOUS)

    val stateFlow: StateFlow<State<T>>
        get() = mutableStateFlow

    fun show(data: T) {
        if (isShownForResult()) {
            resultChannel.trySend(null)
        }
        mutableStateFlow.value = State.Shown(data, forResult = false)
    }

    suspend fun showForResult(data: T): R? {
        if (isShownForResult()) {
            resultChannel.trySend(null)
        }
        mutableStateFlow.value = State.Shown(data, forResult = true)
        return resultChannel.receive()
    }

    fun sendResult(result: R) {
        mutableStateFlow.value = State.Hidden
        this.resultChannel.trySend(result)
    }

    fun dismiss() {
        if (mutableStateFlow.value == State.Hidden) {
            return
        }

        val wasShownForResult = isShownForResult()
        mutableStateFlow.value = State.Hidden
        if (wasShownForResult) {
            resultChannel.trySend(null)
        }
    }

    private fun isShownForResult(): Boolean {
        return (mutableStateFlow.value as? State.Shown<T>)?.forResult == true
    }
}

fun <R : Any> DialogControl<Unit, R>.show() = show(Unit)

suspend fun <R : Any> DialogControl<Unit, R>.showForResult(): R? = showForResult(Unit)

val <T : Any, R : Any> DialogControl<T, R>.dataOrNull: T?
    get() = (this.stateFlow.value as? DialogControl.State.Shown)?.data
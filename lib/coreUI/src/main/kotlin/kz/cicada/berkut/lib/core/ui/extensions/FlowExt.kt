package kz.cicada.berkut.lib.core.ui.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.cicada.berkut.lib.core.ui.base.ViewState

fun <T> Flow<T>.launchWithLifecycle(
    lifecycleOwner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
) {
    lifecycleOwner.lifecycleScope.launch {
        flowWithLifecycle(
            lifecycleOwner.lifecycle,
            minActiveState,
        ).collect()
    }
}

fun <T> StateFlow<T>.tryToUpdate(function: (T) -> T) {
    if (this is MutableStateFlow<T>) {
        this.update(function)
    }
}

fun <T : Any> MutableStateFlow<ViewState<T>>.updateData(block: T.() -> T) {
    if (this.value is ViewState.Data<T>) {
        this.update { viewState ->
            val data = viewState as ViewState.Data<T>
            data.copy(data = block.invoke(data.data))
        }
    }
}

fun <T : Any> StateFlow<ViewState<T>>.tryUpdateData(block: T.() -> T) {
    val flow = this
    if (flow is MutableStateFlow<ViewState<T>> && flow.value is ViewState.Data<T>) {
        flow.updateData(block)
    }
}

fun <S : Any, D : S> MutableStateFlow<ViewState<S>>.updateInnerData(block: D.() -> D) {
    if (this.value is ViewState.Data<S>) {
        this.update { viewState ->
            val data = viewState as ViewState.Data<D>
            data.copy(data = block.invoke(data.data))
        }
    }
}

fun <S : Any, D : S> StateFlow<ViewState<S>>.tryUpdateInnerData(block: D.() -> D) {
    if (this is MutableStateFlow<ViewState<S>>) {
        this.updateInnerData(block)
    }
}

fun <T : Any> MutableStateFlow<ViewState<T>>.updateError(
    exception: Throwable,
    block: T.() -> T,
) {
    if (this.value is ViewState.Error<T>) {
        this.update { viewState ->
            val error = viewState as ViewState.Error<T>
            error.copy(
                error = exception,
                errorData = error.errorData?.let { block.invoke(it) },
            )
        }
    }
}

val <T : Any> StateFlow<ViewState<T>>.extractDataOrNull
    get() = this.value.dataOrNull
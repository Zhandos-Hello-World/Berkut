package kz.cicada.berkut.lib.core.ui.base

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.core.presentation.R
import kz.cicada.berkut.lib.core.error.handling.ErrorHandler
import kz.cicada.berkut.lib.core.ui.event.BlockingLoaderEvent
import kz.cicada.berkut.lib.core.ui.event.CloseAppEvent
import kz.cicada.berkut.lib.core.ui.event.CommonErrorEvent
import kz.cicada.berkut.lib.core.ui.event.Event
import kz.cicada.berkut.lib.core.ui.event.ShowAlertDialogEvent
import kz.cicada.berkut.lib.core.ui.event.SystemEvent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import timber.log.Timber
import kotlin.coroutines.cancellation.CancellationException

abstract class BaseViewModel : ViewModel(), KoinComponent {

    private val errorHandler: ErrorHandler by inject()
    val actionEvents = MutableLiveData<Event<SystemEvent>>()
    val isLoading = MutableLiveData(false)

    open fun onNavigationResult(result: Any) {}

    fun sendEvent(vararg actionEvent: SystemEvent) {
        actionEvent.forEach {
            actionEvents.postValue(Event(it))
        }
    }

    fun fireCommonError(@StringRes message: Int) {
        sendEvent(CommonErrorEvent(message))
    }

    fun confirmCloseApplication() {
        sendEvent(
            ShowAlertDialogEvent(
                title = VmRes.StrRes(R.string.do_really_want_to_exit_from_app),
                positiveButton = VmRes.StrRes(R.string.do_exit_from_app),
                positiveButtonClick = ::closeApplication,
                negativeButton = VmRes.StrRes(R.string.cancel_exit_from_app),
            ),
        )
    }

    fun closeApplication() {
        sendEvent(CloseAppEvent)
    }

    fun <T> networkBlockingRequest(
        request: suspend () -> T,
        onSuccess: suspend (T) -> Unit = {},
        onError: (suspend (Throwable) -> Unit)? = null,
        finally: () -> Unit = { },
    ) = viewModelScope.launch {
        showBlockingLoader()
        try {
            val response = request()
            hideBlockingLoader()
            onSuccess(response)
        } catch (ex: Throwable) {
            hideBlockingLoader()
            try {
                onError?.invoke(ex) ?: throw ex
            } catch (ex: Throwable) {
                Timber
                    .tag(this@BaseViewModel::class.java.simpleName + "\$networkBlockingRequest")
                    .e(ex)
            }
        } finally {
            finally.invoke()
        }
    }

    fun <T> networkBlockingRequest(
        request: suspend () -> T,
    ) = networkBlockingRequest(
        request = request,
        onSuccess = {},
        onError = null,
    )

    fun <T> networkRequest(
        request: suspend () -> T,
        onSuccess: suspend (T) -> Unit = {},
        onError: (suspend (Throwable) -> Unit)? = null,
        finally: () -> Unit = { },
        loadingState: MutableLiveData<Boolean>? = isLoading,
    ) = viewModelScope.launch {
        if (loadingState?.value == null || loadingState.value == false) {
            loadingState?.value = true

            try {
                val response = request()
                loadingState?.value = false
                onSuccess(response)
            } catch (ex: Throwable) {
                loadingState?.value = false

                try {
                    onError?.invoke(ex)
                } catch (ex: Throwable) {
                    Timber
                        .tag(this@BaseViewModel::class.java.simpleName + "\$networkRequest")
                        .e(ex)
                }
            } finally {
                finally.invoke()
            }
        }
    }

    fun <T> networkSilentRequest(
        start: CoroutineStart = CoroutineStart.DEFAULT,
        request: suspend () -> T,
    ) = viewModelScope.launch(start = start) {
        try {
            request()
        } catch (ex: Throwable) {
            Timber
                .tag(this@BaseViewModel::class.java.simpleName + "\$networkSilentRequest")
                .e(ex)
        }
    }


    fun <T> dataRequest(
        dispatcher: CoroutineDispatcher = Dispatchers.Default,
        loading: ((Boolean) -> Unit)? = null,
        request: suspend () -> T,
        onSuccess: ((T) -> Unit)? = null,
        shouldUseBasicErrorHandler: Boolean = true,
        onError: ((Throwable) -> Unit)? = null,
        finally: (() -> Unit)? = null,
    ) = viewModelScope.launch(dispatcher) {
        try {
            loading?.invoke(true)
            val response = request()
            loading?.invoke(false)
            onSuccess?.invoke(response)
        } catch (e: CancellationException) {
            loading?.invoke(false)
        } catch (e: Throwable) {
            loading?.invoke(false)
            onError?.invoke(e)
            if (shouldUseBasicErrorHandler) {
                errorHandler.handleError(e)
            }
        } finally {
            finally?.invoke()
        }
    }


    fun showBlockingLoader() {
        sendEvent(BlockingLoaderEvent(true))
    }

    fun hideBlockingLoader() {
        sendEvent(BlockingLoaderEvent(false))
    }
}
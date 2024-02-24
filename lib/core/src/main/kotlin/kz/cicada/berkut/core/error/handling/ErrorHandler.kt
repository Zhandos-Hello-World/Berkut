package kz.cicada.berkut.core.error.handling

import kz.cicada.berkut.core.localization.string.VmRes
import kz.cicada.berkut.core.message.Message
import kz.cicada.berkut.core.message.MessageHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kz.cicada.berkut.lib.core.R
import timber.log.Timber

class ErrorHandler(
    private val messageHandler: MessageHandler,
) {

    private val unauthorizedEventChannel = Channel<Unit>(Channel.UNLIMITED)

    val unauthorizedEventFlow = unauthorizedEventChannel.receiveAsFlow()

    fun handleError(throwable: Throwable, showError: Boolean = true) {
        Timber.e(throwable)
        when {
            throwable is UnauthorizedException && throwable.status == UNAUTHORIZED -> {
                unauthorizedEventChannel.trySend(Unit)
            }

            showError -> {
                messageHandler.showMessage(
                    Message(
                        title = VmRes.StrRes(R.string.error_title),
                        description = throwable.errorMessage,
                        positiveButtonText = VmRes.StrRes(R.string.common_close),
                    ),
                )
            }
        }
    }
}
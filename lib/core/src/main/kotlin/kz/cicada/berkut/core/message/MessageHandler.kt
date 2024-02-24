package kz.cicada.berkut.core.message

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class MessageHandler {

    private val channel = Channel<Message>(Channel.UNLIMITED)

    val messageFlow = channel.receiveAsFlow()

    fun showMessage(message: Message) {
        channel.trySend(message)
    }
}
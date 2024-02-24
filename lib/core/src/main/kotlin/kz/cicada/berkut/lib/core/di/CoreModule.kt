package kz.cicada.berkut.lib.core.di

import kz.cicada.berkut.lib.core.error.handling.ErrorHandler
import kz.cicada.berkut.lib.core.message.MessageHandler
import org.koin.dsl.module

val coreModule = module {
    single { MessageHandler() }
    single {
        ErrorHandler(
            messageHandler = get(),
        )
    }
}
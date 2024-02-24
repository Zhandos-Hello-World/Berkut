package kz.cicada.berkut.core.di

import kz.cicada.berkut.core.error.handling.ErrorHandler
import kz.cicada.berkut.core.message.MessageHandler
import org.koin.dsl.module

val coreModule = module {
    single { MessageHandler() }
    single {
        ErrorHandler(
            messageHandler = get(),
        )
    }
}
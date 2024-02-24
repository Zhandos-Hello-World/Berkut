package kz.cicada.berkut.lib.core.data.di

import kz.cicada.berkut.lib.core.data.network.NetworkApiFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun coreDataModule(backendUrl: String) = module {
    single { NetworkApiFactory(url = backendUrl, context = androidContext()) }
}
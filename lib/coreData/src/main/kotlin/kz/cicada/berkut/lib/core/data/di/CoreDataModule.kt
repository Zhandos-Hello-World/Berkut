package kz.cicada.berkut.lib.core.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kz.cicada.berkut.lib.core.data.DefaultDeviceUtils
import kz.cicada.berkut.lib.core.data.DeviceUtils
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.data.local.dataStore
import kz.cicada.berkut.lib.core.data.network.AuthorizationInterceptor
import kz.cicada.berkut.lib.core.data.network.NetworkApiFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun coreDataModule(backendUrl: String) = module {
    single {
        NetworkApiFactory(
            url = backendUrl,
            interceptor = get(),
            context = androidContext(),
        )
    }

    single<DataStore<Preferences>> { androidContext().dataStore }


    single<UserPreferences> {
        UserPreferences(
            dataStore = get(),
        )
    }

    factory<DeviceUtils> {
        DefaultDeviceUtils(
            context = get(),
        )
    }

    single {
        AuthorizationInterceptor(
            dataStore = get(),
        )
    }
}
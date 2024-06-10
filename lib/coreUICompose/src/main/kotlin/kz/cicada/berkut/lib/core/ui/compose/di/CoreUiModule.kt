package kz.cicada.berkut.lib.core.ui.compose.di

import kz.cicada.berkut.lib.core.ui.compose.activity.ActivityProvider
import kz.cicada.berkut.lib.core.ui.compose.external.app.service.DefaultExternalAppService
import kz.cicada.berkut.lib.core.ui.compose.external.app.service.ExternalAppService
import kz.cicada.berkut.lib.core.ui.compose.system.DeviceAppService
import kz.cicada.berkut.lib.core.ui.compose.system.ExternalDeviceAppService
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreUiModule = module {
    single { ActivityProvider() }
    single<ExternalAppService> {
        DefaultExternalAppService(
            context = androidApplication().applicationContext,
            activityProvider = get(),
            messageHandler = get(),
        )
    }
    single<DeviceAppService> { ExternalDeviceAppService(androidContext()) }
}
package kz.cicada.berkut.feature.location.di

import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kz.cicada.berkut.feature.location.data.location.LocationApi
import kz.cicada.berkut.feature.location.data.repository.LocationRepository
import kz.cicada.berkut.feature.location.external.DefaultExternalLocationService
import kz.cicada.berkut.feature.location.external.ExternalLocationService
import kz.cicada.berkut.feature.location.service.DefaultLocationClient
import kz.cicada.berkut.feature.location.service.LocationClient
import kz.cicada.berkut.lib.core.data.network.NetworkApiFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val geolocationModule = module {

    factory<LocationApi> {
        get<NetworkApiFactory>().createAuthorizedApi<LocationApi>()
    }

    factory<LocationRepository> {
        LocationRepository(
            defaultUserPreferences = get(),
            dispatcher = Dispatchers.IO,
            api = get(),
            deviceAppService = get(),
        )
    }

    single<LocationClient> {
        DefaultLocationClient(
            context = androidContext().applicationContext,
            client = LocationServices.getFusedLocationProviderClient(androidContext().applicationContext),
        )
    }

    single<ExternalLocationService> {
        DefaultExternalLocationService(
            context = androidApplication().applicationContext,
            activityProvider = get(),
            messageHandler = get(),
        )
    }
}

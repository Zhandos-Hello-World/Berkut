package kz.cicada.berkut.di

import am.strongte.hub.auth.di.authModule
import android.app.Application
import kz.cicada.berkut.BuildConfig
import kz.cicada.berkut.lib.core.data.di.coreDataModule
import kz.cicada.berkut.lib.core.di.coreModule
import kz.cicada.berkut.lib.core.ui.compose.di.coreUiModule
import kz.cicada.berkut.lib.core.ui.di.corePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal object KoinModules {

	private val appModules = listOf(
		appNavigationModule,
		appPresentationModule,
	)
	private val featureModules = listOf(
		authModule,
	)
	private val libraryModules = listOf(
		coreModule,
		corePresentationModule,
		coreUiModule,
		coreDataModule(BuildConfig.BERKUT_BASE_URL)
	)

	fun start(application: Application) {
		startKoin {
			androidContext(application)
			modules(
				*appModules.toTypedArray(),
				*featureModules.toTypedArray(),
				*libraryModules.toTypedArray(),
			)
		}
	}
}
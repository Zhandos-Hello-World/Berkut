package kz.cicada.berkut.di

import kz.cicada.berkut.feature.chooser.presentation.di.chooserModule
import android.app.Application
import kz.cicada.berkut.BuildConfig
import kz.cicada.berkut.feature.auth.di.authModule
import kz.cicada.berkut.feature.result.di.resultModule
import kz.cicada.berkut.lib.core.data.di.coreDataModule
import kz.cicada.berkut.lib.core.di.coreModule
import kz.cicada.berkut.lib.core.ui.compose.di.coreUiModule
import kz.cicada.berkut.lib.core.ui.di.corePresentationModule
import kz.cicada.berkut.feature.language.di.languageModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal object KoinModules {

	private val appModules = listOf(
		appNavigationModule,
		appPresentationModule,
	)
	private val featureModules = listOf(
		authModule,
		resultModule,
		chooserModule,
		languageModule,
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
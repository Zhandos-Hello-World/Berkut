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
import kz.cicada.berkut.feature.location.di.geolocationModule
import kz.cicada.berkut.feature.maps.di.mapsModule
import kz.cicada.berkut.feature.shareqr.di.qrModule
import kz.cicada.berkut.feature.uploadphoto.di.addAvatarModule
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
		mapsModule,
		qrModule,
		addAvatarModule,
		geolocationModule,
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
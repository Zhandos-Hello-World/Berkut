package kz.cicada.berkut.di

import kz.cicada.berkut.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appPresentationModule = module {

	viewModel {
		MainActivityViewModel()
	}
}
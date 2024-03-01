package kz.cicada.berkut.feature.home.di

import kz.cicada.berkut.feature.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val homeModule = module {

    viewModel { HomeViewModel() }
}
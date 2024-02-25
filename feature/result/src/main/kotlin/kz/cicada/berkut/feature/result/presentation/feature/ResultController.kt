package kz.cicada.berkut.feature.result.presentation.feature

import androidx.compose.runtime.Stable

@Stable
interface ResultController {

    fun onPrimaryButtonClick()

    fun onNavigateBack()

}
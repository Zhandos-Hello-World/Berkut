package kz.cicada.berkut.feature.auth.presentation.input.password

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.feature.auth.presentation.common.AuthFlow

@Parcelize
internal data class InputPasswordLauncher(
    val flow: AuthFlow,
    val behavior: InputPasswordBehavior,
) : Parcelable
package kz.cicada.berkut.feature.auth.presentation.input.email

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.feature.auth.presentation.common.AuthFlow

@Parcelize
internal data class InputEmailLauncher(
    val flow: AuthFlow,
    val behavior: InputEmailBehavior,
) : Parcelable
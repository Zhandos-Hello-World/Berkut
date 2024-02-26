package kz.cicada.berkut.feature.auth.presentation.name

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.feature.auth.presentation.common.AuthFlow
import kz.cicada.berkut.feature.auth.presentation.input.email.InputEmailBehavior

@Parcelize
internal data class InputNameLauncher(
    val flow: AuthFlow,
    val behavior: InputEmailBehavior,
) : Parcelable
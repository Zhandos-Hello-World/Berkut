package am.strongte.hub.auth.presentation.input.password

import am.strongte.hub.auth.presentation.common.AuthFlow
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class InputPasswordLauncher(
    val flow: AuthFlow,
    val behavior: InputPasswordBehavior,
) : Parcelable
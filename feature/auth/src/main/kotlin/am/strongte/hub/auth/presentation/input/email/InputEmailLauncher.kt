package am.strongte.hub.auth.presentation.input.email

import am.strongte.hub.auth.presentation.common.AuthFlow
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class InputEmailLauncher(
    val flow: AuthFlow,
    val behavior: InputEmailBehavior,
) : Parcelable
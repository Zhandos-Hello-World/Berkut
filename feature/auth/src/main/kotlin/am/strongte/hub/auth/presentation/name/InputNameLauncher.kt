package am.strongte.hub.auth.presentation.name

import am.strongte.hub.auth.presentation.common.AuthFlow
import am.strongte.hub.auth.presentation.input.email.InputEmailBehavior
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class InputNameLauncher(
    val flow: AuthFlow,
    val behavior: InputEmailBehavior,
) : Parcelable
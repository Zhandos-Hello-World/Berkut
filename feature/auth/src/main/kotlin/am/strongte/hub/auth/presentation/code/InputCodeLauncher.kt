package am.strongte.hub.auth.presentation.code

import am.strongte.hub.auth.presentation.common.AuthFlow
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal class InputCodeLauncher(
    val flow: AuthFlow,
    val email: String,
) : Parcelable
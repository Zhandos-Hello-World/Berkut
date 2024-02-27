package kz.cicada.berkut.feature.auth.presentation.code

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.feature.auth.domain.model.LoginParams
import kz.cicada.berkut.feature.auth.domain.model.UserType
import kz.cicada.berkut.feature.auth.presentation.common.AuthFlow

@Parcelize
internal class InputCodeLauncher(
    val flow: AuthFlow,
    val params: LoginParams,
) : Parcelable
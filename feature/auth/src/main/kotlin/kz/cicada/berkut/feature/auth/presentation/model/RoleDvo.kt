package kz.cicada.berkut.feature.auth.presentation.model

import androidx.annotation.DrawableRes
import kz.cicada.berkut.lib.core.data.network.UserType
import kz.cicada.berkut.lib.core.localization.string.VmRes

data class RoleDvo(
    val title: VmRes<CharSequence>,
    @DrawableRes val icon: Int,
    val type: UserType,
)

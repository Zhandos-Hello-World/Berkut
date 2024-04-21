package kz.cicada.berkut.feature.profile.presentation.logout

import kz.cicada.berkut.lib.core.ui.event.ActionEvent

data class LogoutConfirmEvent(
    val logout: Boolean
) : ActionEvent()
package kz.cicada.berkut.feature.auth.presentation.reset.password

import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.feature.auth.navigation.AuthScreens
import kz.cicada.berkut.feature.result.R
import kz.cicada.berkut.feature.result.presentation.feature.ResultBehavior
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.event.OpenScreenEvent
import kz.cicada.berkut.lib.core.ui.event.SystemEvent

@Parcelize
internal class ResetPasswordResultBehavior : ResultBehavior {
    override fun getTitle(): VmRes<CharSequence> =
        VmRes.StrRes(R.string.password_changed)

    override fun getBody(): VmRes<CharSequence> =
        VmRes.StrRes(R.string.do_not_forget_password_please)

    override fun getPrimaryButtonText(): VmRes<CharSequence> =
        VmRes.StrRes(R.string.authorize)

    override suspend fun onPrimaryButtonClick(): List<SystemEvent> = listOf(
//        OpenScreenEvent(AuthScreens.Login()),
    )

    override suspend fun onNavigateBack(): List<SystemEvent> = listOf(
//        OpenScreenEvent(AuthScreens.Login()),
    )
}
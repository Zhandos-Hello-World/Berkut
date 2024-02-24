package am.strongte.hub.auth.presentation.registration.result

import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.lib.core.localization.string.VmRes

//@Parcelize
//internal class RegistrationResultBehavior : ResultBehavior {
//    override fun getTitle(): VmRes<CharSequence> =
//        VmRes.StrRes(am.strongte.features.result.R.string.account_successfully_created)
//
//    override fun getBody(): VmRes<CharSequence> =
//        VmRes.StrRes(am.strongte.features.result.R.string.login_and_tell_about_yourself)
//
//    override fun getPrimaryButtonText(): VmRes<CharSequence> =
//        VmRes.StrRes(am.strongte.features.result.R.string.authorize)
//
//    override suspend fun onPrimaryButtonClick(): List<SystemEvent> = listOf(
//        OpenNewRootScreenEvent(LoginScreen()),
//    )
//
//    override suspend fun onNavigateBack(): List<SystemEvent> = listOf(
//        OpenNewRootScreenEvent(LoginScreen()),
//    )
//}
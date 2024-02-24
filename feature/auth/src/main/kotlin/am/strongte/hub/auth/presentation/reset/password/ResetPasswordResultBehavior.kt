package am.strongte.hub.auth.presentation.reset.password

//import am.strongte.core.localization.string.VmRes
//import am.strongte.core.ui.event.SystemEvent
//import am.strongte.core.ui.navigation.OpenNewRootScreenEvent
//import am.strongte.hub.auth.presentation.login.LoginScreen
//import am.strongte.result.presentation.feature.ResultBehavior
//import kotlinx.parcelize.Parcelize
//
//@Parcelize
//internal class ResetPasswordResultBehavior : ResultBehavior {
//    override fun getTitle(): VmRes<CharSequence> =
//        VmRes.StrRes(am.strongte.features.result.R.string.password_changed)
//
//    override fun getBody(): VmRes<CharSequence> =
//        VmRes.StrRes(am.strongte.features.result.R.string.do_not_forget_password_please)
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
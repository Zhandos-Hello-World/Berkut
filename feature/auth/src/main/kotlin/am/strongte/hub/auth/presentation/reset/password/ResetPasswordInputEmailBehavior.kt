package am.strongte.hub.auth.presentation.reset.password

//import am.strongte.core.localization.string.VmRes
//import am.strongte.core.ui.event.SystemEvent
//import am.strongte.core.ui.navigation.OpenScreenEvent
//import am.strongte.features.auth.R
//import am.strongte.hub.auth.presentation.code.InputCodeLauncher
//import am.strongte.hub.auth.presentation.code.InputCodeScreen
//import am.strongte.hub.auth.presentation.common.AuthFlow
//import am.strongte.hub.auth.presentation.input.email.InputEmailBehavior
//import kotlinx.coroutines.delay
//import kotlinx.parcelize.Parcelize
//
//@Parcelize
//internal object ResetPasswordInputEmailBehavior : InputEmailBehavior {
//    override fun getTitle(): VmRes<CharSequence> {
//        return VmRes.StrRes(R.string.reset_password_title)
//    }
//
//    override fun getDescription(): VmRes<CharSequence> {
//        return VmRes.StrRes(R.string.input_corporate_email)
//    }
//
//    override fun getPrimaryButtonText(): VmRes<CharSequence> {
//        return VmRes.StrRes(R.string.reset)
//    }
//
//    override suspend fun onPrimaryButtonClick(value: String): List<SystemEvent> {
//        // Отправляем запрос на Backend, возможно делаем валидацию
//        delay(3000L)
//        return listOf(
//            OpenScreenEvent(
//                InputCodeScreen(
//                    InputCodeLauncher(email = value, flow = AuthFlow.ResetPassword),
//                ),
//            ),
//        )
//    }
//}
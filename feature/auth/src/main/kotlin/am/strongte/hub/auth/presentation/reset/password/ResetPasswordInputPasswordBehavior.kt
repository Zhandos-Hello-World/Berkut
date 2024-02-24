package am.strongte.hub.auth.presentation.reset.password

//import am.strongte.core.empty
//import am.strongte.core.localization.string.VmRes
//import am.strongte.core.ui.event.SystemEvent
//import am.strongte.features.auth.R
//import am.strongte.hub.auth.presentation.input.password.InputPasswordBehavior
//import am.strongte.hub.auth.presentation.input.password.PasswordFieldData
//import androidx.compose.ui.text.input.ImeAction
//import kotlinx.parcelize.Parcelize
//
//@Parcelize
//internal class ResetPasswordInputPasswordBehavior : InputPasswordBehavior {
//
//    override suspend fun onPrimaryButtonClick(value: String): List<SystemEvent> {
//        return emptyList()
//    }
//
//    override fun getInputItems(): List<PasswordFieldData> {
//        return listOf(
//            PasswordFieldData(
//                value = String.empty,
//                label = VmRes.StrRes(R.string.new_password),
//                imeAction = ImeAction.Next,
//            ),
//            PasswordFieldData(
//                value = String.empty,
//                label = VmRes.StrRes(R.string.confirm_password),
//                imeAction = ImeAction.Done,
//            ),
//        )
//    }
//
//    override fun getPrimaryButtonText(): VmRes<CharSequence> {
//        return VmRes.StrRes(R.string.change)
//    }
//
//    override fun validateFields(fieldsData: List<PasswordFieldData>): List<PasswordFieldData>? {
//        return null
//    }
//}
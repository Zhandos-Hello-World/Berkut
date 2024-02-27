package kz.cicada.berkut.feature.auth.presentation.reset.password

import kotlinx.coroutines.delay
import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.feature.auth.R
import kz.cicada.berkut.feature.auth.domain.model.LoginParams
import kz.cicada.berkut.feature.auth.presentation.input.email.InputEmailBehavior
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.event.SystemEvent

@Parcelize
internal object ResetPasswordInputEmailBehavior : InputEmailBehavior {
    override fun getTitle(): VmRes<CharSequence> {
        return VmRes.StrRes(R.string.reset_password_title)
    }

    override fun getDescription(): VmRes<CharSequence> {
        return VmRes.StrRes(R.string.input_corporate_email)
    }

    override fun getPrimaryButtonText(): VmRes<CharSequence> {
        return VmRes.StrRes(R.string.reset)
    }

    override suspend fun onPrimaryButtonClick(params: LoginParams): List<SystemEvent> {
        // Отправляем запрос на Backend, возможно делаем валидацию
        delay(3000L)
        return emptyList()
    }
}
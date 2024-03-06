package kz.cicada.berkut.feature.auth.presentation.registration.email

import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.feature.auth.R
import kz.cicada.berkut.feature.auth.domain.model.LoginParams
import kz.cicada.berkut.feature.auth.domain.repository.ValidationRepository
import kz.cicada.berkut.feature.auth.navigation.AuthScreens
import kz.cicada.berkut.feature.auth.presentation.code.InputCodeLauncher
import kz.cicada.berkut.feature.auth.presentation.common.AuthFlow
import kz.cicada.berkut.feature.auth.presentation.input.email.InputEmailBehavior
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.event.OpenScreenEvent
import kz.cicada.berkut.lib.core.ui.event.SystemEvent
import org.koin.core.component.inject

@Parcelize
internal object RegistrationInputEmailBehavior : InputEmailBehavior {

    @IgnoredOnParcel
    private val repository: ValidationRepository by inject()

    override fun getTitle(): VmRes<CharSequence> {
        return VmRes.StrRes(R.string.registration)
    }

    override fun getDescription(): VmRes<CharSequence> {
        return VmRes.StrRes(R.string.registration_description)
    }

    override fun getPrimaryButtonText(): VmRes<CharSequence> {
        return VmRes.StrRes(R.string.continue_text)
    }

    override suspend fun onPrimaryButtonClick(params: LoginParams): List<SystemEvent> {
        val argParams = params.copy(phoneNumber = params.phoneNumber.substring(1))
        repository.validatePhone(argParams.phoneNumber)
        return listOf(
            OpenScreenEvent(
                AuthScreens.InputCode(
                    InputCodeLauncher(
                        flow = AuthFlow.Registration,
                        params = argParams,
                    ),
                ),
            )
        )
    }
}
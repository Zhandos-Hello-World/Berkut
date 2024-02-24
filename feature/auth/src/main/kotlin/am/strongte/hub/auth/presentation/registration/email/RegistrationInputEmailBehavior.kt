package am.strongte.hub.auth.presentation.registration.email

import am.strongte.hub.auth.domain.repository.ValidationRepository
import am.strongte.hub.auth.presentation.input.email.InputEmailBehavior
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.feature.auth.R
import kz.cicada.berkut.lib.core.localization.string.VmRes
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

    override suspend fun onPrimaryButtonClick(value: String): List<SystemEvent> {
        repository.validateEmail(value)
        return listOf(
//            OpenScreenEvent(
//                InputCodeScreen(
//                    InputCodeLauncher(email = value, flow = AuthFlow.Registration),
//                ),
//            ),
        )
    }
}
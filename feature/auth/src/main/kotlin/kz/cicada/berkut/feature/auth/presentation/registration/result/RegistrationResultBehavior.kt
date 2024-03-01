package kz.cicada.berkut.feature.auth.presentation.registration.result

import kotlinx.coroutines.flow.first
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kz.cicada.berkut.feature.auth.domain.model.UserType
import kz.cicada.berkut.feature.result.R
import kz.cicada.berkut.feature.result.presentation.feature.ResultBehavior
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.event.OpenMainActivityEvent
import kz.cicada.berkut.lib.core.ui.event.SystemEvent
import org.koin.core.component.inject

@Parcelize
internal class RegistrationResultBehavior : ResultBehavior {
    @IgnoredOnParcel
    private val pref: UserPreferences by inject()

    override fun getTitle(): VmRes<CharSequence> =
        VmRes.StrRes(R.string.account_successfully_created)

    override fun getBody(): VmRes<CharSequence> =
        VmRes.StrRes(R.string.login_and_tell_about_yourself)

    override fun getPrimaryButtonText(): VmRes<CharSequence> = VmRes.StrRes(kz.cicada.berkut.core.presentation.R.string.to_main_page)

    override suspend fun onPrimaryButtonClick(): List<SystemEvent> {
        val type = when (pref.getType().first()) {
            UserType.PARENT.name -> UserType.PARENT
            UserType.CHILD.name -> UserType.CHILD
            else ->  UserType.PARENT
        }
        return listOf(
            OpenMainActivityEvent
        )
    }

    override suspend fun onNavigateBack(): List<SystemEvent> = listOf(
//        OpenScreenEvent(AuthScreens.Login()),
    )
}
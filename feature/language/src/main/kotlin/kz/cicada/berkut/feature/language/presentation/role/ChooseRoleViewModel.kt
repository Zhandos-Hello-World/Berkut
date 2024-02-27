package kz.cicada.berkut.feature.language.presentation.role

import kotlinx.coroutines.flow.MutableStateFlow
import kz.cicada.berkut.feature.auth.domain.model.UserType
import kz.cicada.berkut.feature.auth.navigation.AuthScreens
import kz.cicada.berkut.feature.language.R
import kz.cicada.berkut.lib.core.localization.string.VmRes
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade
import kz.cicada.berkut.feature.language.presentation.model.RoleDvo
import kz.cicada.berkut.lib.core.ui.compose.R as composeR

class ChooseRoleViewModel(
    private val routerFacade: RouterFacade,
) : BaseViewModel(), ChooseRoleController {
    val uiState: MutableStateFlow<ChooseRoleUIState> = MutableStateFlow(
        ChooseRoleUIState(
            roles = listOf(
                RoleDvo(
                    title = VmRes.StrRes(R.string.child),
                    icon = composeR.drawable.ic_lucky_avatar,
                    type = UserType.CHILD,
                ), RoleDvo(
                    title = VmRes.StrRes(R.string.parent),
                    icon = composeR.drawable.ic_lucky_motivating_yoda,
                    type = UserType.PARENT,
                )
            ),
            selected = null,
        ),
    )

    override fun onConfirmClick() {
        val selected = uiState.value.selected ?: return
        routerFacade.navigateTo(AuthScreens.Login(selected.type))
    }

    override fun onNavigateBack() {
        routerFacade.exit()
    }

    override fun onSelectRole(role: RoleDvo) {
        uiState.tryToUpdate {
            it.copy(
                selected = role,
            )
        }
    }
}
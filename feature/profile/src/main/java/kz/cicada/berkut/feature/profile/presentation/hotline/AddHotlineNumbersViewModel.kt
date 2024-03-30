package kz.cicada.berkut.feature.profile.presentation.hotline

import kotlinx.coroutines.flow.MutableStateFlow
import kz.cicada.berkut.feature.profile.data.model.AddHotlineNumberRequest
import kz.cicada.berkut.feature.profile.data.network.AddHotlineNumbersApi
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade

class AddHotlineNumbersViewModel(
    private val routerFacade: RouterFacade,
    private val api: AddHotlineNumbersApi,
) : BaseViewModel(), AddHotlineNumberController {
    val state = MutableStateFlow(
        AddHotlineNumberState.Data(
            "",
            "",
        )
    )

    override fun onNavigateBack() = routerFacade.exit()

    override fun addHotlineNumber() {
        val name = state.value.name
        val phoneNumber = state.value.phoneNumber

        if (name.isEmpty() || phoneNumber.isEmpty()) {
            return
        }
        networkRequest(
            request = {
                api.addHotlineNumbers(
                    request = AddHotlineNumberRequest(
                        phoneNumber = phoneNumber,
                        name = name,
                    )
                )
            },
            finally = {
                onNavigateBack()
            },
        )
    }

    override fun inputNameOfHotline(name: String) {
        state.tryToUpdate {
            it.copy(
                name = name,
            )
        }
    }

    override fun inputPhoneNumber(phoneNumber: String) {
        state.tryToUpdate {
            it.copy(
                phoneNumber = phoneNumber,
            )
        }
    }
}
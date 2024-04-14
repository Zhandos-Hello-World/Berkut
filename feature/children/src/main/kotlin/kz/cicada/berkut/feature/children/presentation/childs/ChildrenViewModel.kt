package kz.cicada.berkut.feature.children.presentation.childs

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kz.cicada.berkut.feature.children.domain.repository.ChildrenRepository
import kz.cicada.berkut.feature.shareqr.navigation.QRScreens
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.data.network.UserType
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.event.CloseScreenEvent
import kz.cicada.berkut.lib.core.ui.event.OpenScreenEvent
import kz.cicada.berkut.lib.core.ui.extensions.tryToUpdate

class ChildrenViewModel(
    private val launcher: ChildrenLauncher,
    private val userPreferences: UserPreferences,
    private val repository: ChildrenRepository,
) : BaseViewModel(), ChildrenController {
    val uiState = MutableStateFlow<ChildrenUIState>(ChildrenUIState.Loading)

    fun getChildren() {
        viewModelScope.launch {
            when (userPreferences.getType().first()) {
                UserType.PARENT.name -> networkRequest(
                    request = {
                        val id = userPreferences.getId().first().toInt()
                        repository.getChildren(
                            parentId = id,
                        )
                    },
                    onSuccess = { response ->
                        uiState.tryToUpdate {
                            ChildrenUIState.Data(
                                list = response,
                            )
                        }
                    },
                )

                else -> {
                    val childId = userPreferences.getId().first().toInt()
                    launcher.behavior.onClickNavigate(childId).forEach(::sendEvent)
                }
            }
        }
    }

    override fun navigateUp() {
        sendEvent(CloseScreenEvent)
    }

    override fun onChildrenClick(id: Int) {
        launcher.behavior.onClickNavigate(id).forEach(::sendEvent)
    }

    override fun onAddChildClick() {
        sendEvent(
            OpenScreenEvent(
                QRScreens.scanQRScreen(),
            )
        )
    }
}
package kz.cicada.berkut.feature.children.presentation.details

import kotlinx.coroutines.flow.MutableStateFlow
import kz.cicada.berkut.feature.children.domain.repository.ChildrenRepository
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate

class ChildDetailsViewModel(
    private val launcher: ChildDetailsLauncher,
    private val repo: ChildrenRepository,
) : BaseViewModel(), ChildDetailsController {
    val uiState = MutableStateFlow<ChildDetailsUIState.Data>(
        ChildDetailsUIState.Data(
            imageUrl = null,
            username = launcher.username,
            location = launcher.location,
            battery = "${launcher.battery} %",
        )
    )

    init {
        getProfilePhoto()
    }

    private fun getProfilePhoto() {
        networkRequest(
            request = {
                repo.getChildPhoto(launcher.id)
            },
            onSuccess = { response ->
                val dataState = uiState.value as? ChildDetailsUIState.Data ?: return@networkRequest
                uiState.tryToUpdate {
                    dataState.copy(
                        imageUrl = response,
                    )
                }
            },
        )
    }
}
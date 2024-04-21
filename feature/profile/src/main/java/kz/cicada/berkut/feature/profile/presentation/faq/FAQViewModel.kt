package kz.cicada.berkut.feature.profile.presentation.faq

import kotlinx.coroutines.flow.MutableStateFlow
import kz.cicada.berkut.feature.profile.presentation.faq.factory.FAQFactory
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.event.CloseScreenEvent
import kz.cicada.berkut.lib.core.ui.extensions.tryToUpdate

class FAQViewModel(
    private val factory: FAQFactory,
) : BaseViewModel(), FAQController {
    val uiState = MutableStateFlow<FAQUIState>(FAQUIState.Loading)

    init {
        getData()
    }

    override fun navigateUp() = sendEvent(CloseScreenEvent)

    private fun getData() {
        uiState.tryToUpdate {
            FAQUIState.Data(
                list = factory.create(),
            )
        }
    }

}
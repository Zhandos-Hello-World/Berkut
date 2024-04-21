package kz.cicada.berkut.feature.sos.presentation.hotline.listHotline

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kz.cicada.berkut.feature.sos.R
import kz.cicada.berkut.feature.sos.data.network.HotlineNumbersApi
import kz.cicada.berkut.lib.core.data.local.UserPreferences
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.event.CloseScreenEvent
import kz.cicada.berkut.lib.core.ui.extensions.tryToUpdate

class HotlineListViewModel(
    private val api: HotlineNumbersApi,
    private val userPref: UserPreferences,
) : BaseViewModel(), HotlineListController {
    val state = MutableStateFlow<HotlineListState>(HotlineListState.Loading)

    init {
        getData()
    }

    override fun navigateUp() = sendEvent(CloseScreenEvent)


    private fun getData() {
        networkRequest(
            request = {
                val id = userPref.getId().first()
                api.getHotlineNumbers(id)
            },
            onSuccess = { response ->
                val mappedResponse = response.map {
                    HotlineItem(
                        iconRes = R.drawable.ic_supervisor_account,
                        it.name,
                        it.phoneNumber,
                    )
                }
                state.tryToUpdate {
                    HotlineListState.Data(
                        mappedResponse
                    )
                }
            },
            finally = {
                val existsContacts = getExistContactsFactory()
                state.tryToUpdate {
                    HotlineListState.Data(
                        (it as? HotlineListState.Data)?.list?.plus(existsContacts) ?: existsContacts
                    )
                }
            },
        )
    }

    private fun getExistContactsFactory(): List<HotlineItem> {
        return listOf(
            HotlineItem(
                iconRes = R.drawable.ic_medication,
                name = "103",
                phoneNumber = "103",
            ),
            HotlineItem(
                iconRes = R.drawable.ic_local_police,
                name = "102",
                phoneNumber = "102",
            ),
            HotlineItem(
                iconRes = R.drawable.ic_fire_truck,
                name = "101",
                phoneNumber = "101",
            ),
        )
    }
}
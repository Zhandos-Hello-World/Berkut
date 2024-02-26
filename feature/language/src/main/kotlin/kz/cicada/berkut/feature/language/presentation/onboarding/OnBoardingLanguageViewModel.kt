package kz.cicada.berkut.feature.language.presentation.onboarding

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kz.cicada.berkut.lib.core.error.handling.language.Language
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate
import kz.cicada.berkut.lib.core.ui.event.SystemEvent
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade
import kz.cicada.berkut.feature.language.domain.LanguageRepository
import kz.cicada.berkut.feature.language.navigation.LanguageScreens
import kz.cicada.berkut.feature.language.presentation.chooser.LanguageResultEvent

internal class OnBoardingLanguageViewModel(
    private val repository: LanguageRepository,
    private val routerFacade: RouterFacade,
) : BaseViewModel(), OnBoardingLanguageController {

    private var cachedLanguage: Language = Language.RU
    private val _uiState = MutableStateFlow<OnBoardingLanguageUiState>(
        OnBoardingLanguageUiState.Data(cachedLanguage.resId),
    )

    val uiState: StateFlow<OnBoardingLanguageUiState> = _uiState

    init {
        getLanguageFromLocal()
        handleSystemEvents()
    }

    override fun onLanguageClick() {
        sendEvent(
//            OpenBottomSheetEvent(
//                SimpleChooserScreen(
//                    launcher = SimpleChooserLauncher(
//                        behavior = LanguageChooserBehavior(selectedLanguageName = cachedLanguage.name),
//                    ),
//                ),
//            ),
        )
    }

    override fun onConfirmClick() {
        dataRequest(
            request = {
                when (cachedLanguage.resId) {
                    Language.KK.resId -> repository.setLanguage(Language.KK)
                    Language.RU.resId -> repository.setLanguage(Language.RU)
                }
            },
            onSuccess = {
                routerFacade.navigateTo(LanguageScreens.ChooseRole())
            },
        )
    }

    private fun handleSystemEvents() {
        viewModelScope.launch {
//            eventBus.systemEvents.collect { event ->
//                collectSystemEvents(event)
//            }
        }
    }

    private fun collectSystemEvents(event: SystemEvent) {
        when (event) {
            is LanguageResultEvent -> updateSelectedLanguage(event.result)
            else -> Unit
        }
    }

    private fun updateSelectedLanguage(language: Language) {
        dataRequest(
            request = {
                cachedLanguage = language
                _uiState.tryToUpdate {
                    OnBoardingLanguageUiState.Data(cachedLanguage.resId)
                }
            },
        )
    }

    private fun getLanguageFromLocal() {
        dataRequest(
            request = {
                repository.getLanguage()
            },
            onSuccess = { language ->
                language?.let {
                    cachedLanguage = it
                    _uiState.tryToUpdate {
                        OnBoardingLanguageUiState.Data(
                            resId = cachedLanguage.resId,
                        )
                    }
                }
            },
        )
    }
}

package kz.cicada.berkut.feature.language.presentation.onboarding

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kz.cicada.berkut.feature.auth.navigation.AuthScreens
import kz.cicada.berkut.feature.chooser.presentation.feature.navigation.SimpleChooseScreens
import kz.cicada.berkut.feature.chooser.presentation.feature.simple.SimpleChooserLauncher
import kz.cicada.berkut.feature.language.domain.LanguageRepository
import kz.cicada.berkut.feature.language.presentation.chooser.LanguageChooserBehavior
import kz.cicada.berkut.feature.language.presentation.chooser.LanguageResultEvent
import kz.cicada.berkut.lib.core.error.handling.language.Language
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate
import kz.cicada.berkut.lib.core.ui.event.OpenScreenEvent
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.router.RouterFacade

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
    }

    override fun onLanguageClick() {
        sendEvent(
            OpenScreenEvent(
                SimpleChooseScreens.SimpleChoose(
                    launcher = SimpleChooserLauncher(
                        behavior = LanguageChooserBehavior(selectedLanguageName = cachedLanguage.name)
                    )
                )
            )
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
                routerFacade.navigateTo(AuthScreens.Login())
            },
        )
    }

    override fun onNavigationResult(result: Any) {
        super.onNavigationResult(result)
        when (result) {
            is LanguageResultEvent -> updateSelectedLanguage(result.result)
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

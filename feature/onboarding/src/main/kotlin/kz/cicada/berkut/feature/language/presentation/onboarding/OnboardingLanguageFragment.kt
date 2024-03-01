package kz.cicada.berkut.feature.language.presentation.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.compose.extension.collectAsStateWithLifecycle
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class OnboardingLanguageFragment : ComposeFragment(), FragmentTransition.LeftRight {
    override val viewModel: OnBoardingLanguageViewModel by viewModel()

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        when (val state = uiState) {
            is OnBoardingLanguageUiState.Data -> {
                OnBoardingLanguageContent(controller = viewModel, uiState = state)
            }
        }
    }
}
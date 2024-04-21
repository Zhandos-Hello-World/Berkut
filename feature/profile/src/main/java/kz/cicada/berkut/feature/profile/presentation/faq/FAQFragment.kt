package kz.cicada.berkut.feature.profile.presentation.faq

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FAQFragment : ComposeFragment() {
    override val viewModel: FAQViewModel by viewModel()

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsState()
        FAQContent(
            controller = viewModel,
            uiState = uiState,
        )
    }
}
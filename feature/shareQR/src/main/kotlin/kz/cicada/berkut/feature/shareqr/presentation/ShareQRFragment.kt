package kz.cicada.berkut.feature.shareqr.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeBottomSheetFragment
import kz.cicada.berkut.lib.core.ui.compose.extension.collectAsStateWithLifecycle
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShareQRFragment : ComposeBottomSheetFragment(), FragmentTransition.BottomTop {
    override val viewModel: ShareQRViewModel by viewModel()

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        ShareQRContent(
            uiState = uiState,
            controller = viewModel,
        )
    }
}
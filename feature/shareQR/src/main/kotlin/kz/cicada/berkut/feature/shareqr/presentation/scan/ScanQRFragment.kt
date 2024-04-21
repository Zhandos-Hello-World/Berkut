package kz.cicada.berkut.feature.shareqr.presentation.scan

import androidx.compose.runtime.Composable
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeBottomSheetFragment
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel

class ScanQRFragment : ComposeBottomSheetFragment(), FragmentTransition.LeftRight {
    override val viewModel: ScanQRViewModel by viewModel()

    @Composable
    override fun Content() {
        ScanQRContent(
            controller = viewModel,
        )
    }
}
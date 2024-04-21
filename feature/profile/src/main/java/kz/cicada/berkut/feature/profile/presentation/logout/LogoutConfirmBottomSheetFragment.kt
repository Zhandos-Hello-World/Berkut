package kz.cicada.berkut.feature.profile.presentation.logout

import androidx.compose.runtime.Composable
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeBottomSheetFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class LogoutConfirmBottomSheetFragment : ComposeBottomSheetFragment() {
    override val viewModel: LogoutConfirmViewModel by viewModel()

    @Composable
    override fun Content() {
        LogoutConfirmContent(
            controller = viewModel,
        )
    }
}
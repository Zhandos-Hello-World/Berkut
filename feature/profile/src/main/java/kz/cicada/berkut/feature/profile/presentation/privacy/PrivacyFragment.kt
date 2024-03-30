package kz.cicada.berkut.feature.profile.presentation.privacy

import androidx.compose.runtime.Composable
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.event.CloseScreenEvent

class PrivacyFragment : ComposeFragment() {
    override val viewModel: BaseViewModel? = null

    @Composable
    override fun Content() {
        PrivacyContent(
            onNavigateBack = { onActionEvent(CloseScreenEvent) },
        )
    }
}
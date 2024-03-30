package kz.cicada.berkut.feature.profile.presentation.mission

import androidx.compose.runtime.Composable
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.event.CloseScreenEvent

class OurMissionFragment : ComposeFragment() {
    override val viewModel: BaseViewModel? = null

    @Composable
    override fun Content() {
        OurMissionContent(
            onNavigateBack = { onActionEvent(CloseScreenEvent) },
        )
    }
}
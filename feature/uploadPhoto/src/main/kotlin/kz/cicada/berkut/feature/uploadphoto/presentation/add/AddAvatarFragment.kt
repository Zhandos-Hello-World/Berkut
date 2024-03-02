package kz.cicada.berkut.feature.uploadphoto.presentation.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.compose.extension.collectAsStateWithLifecycle
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class AddAvatarFragment : ComposeFragment(), FragmentTransition.LeftRight {
    override val viewModel: AddAvatarViewModel by viewModel()

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        AddAvatarContent(
            uiState = uiState,
            controller = viewModel,
        )
    }
}
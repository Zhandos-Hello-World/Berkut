package kz.cicada.berkut.feature.children.presentation.childs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ChildrenFragment(
    private val launcher: ChildrenLauncher,
) : ComposeFragment(), FragmentTransition.LeftRight {
    override val viewModel: ChildrenViewModel by viewModel(parameters = { parametersOf(launcher) })

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsState()
        ChildrenContent(
            controller = viewModel,
            uiState = uiState,
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.getChildren()
    }
}
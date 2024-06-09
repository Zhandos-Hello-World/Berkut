package kz.cicada.berkut.feature.children.presentation.operation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kz.cicada.berkut.feature.children.presentation.operation.compose.ChildrenOperationContent
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ChildrenOperationFragment(
    private val launcher: ChildrenOperationLauncher,
): ComposeFragment(), FragmentTransition.LeftRight {
    override val viewModel: ChildrenOperationViewModel by viewModel(parameters = { parametersOf(launcher) })

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsState()
        ChildrenOperationContent(
            controller = viewModel,
            uiState = uiState,
        )
    }
}
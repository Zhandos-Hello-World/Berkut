package am.strongte.hub.auth.presentation.code

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.compose.extension.collectAsStateWithLifecycle
import kz.cicada.berkut.lib.core.ui.extensions.launcherLazy
import kz.cicada.berkut.lib.core.ui.extensions.setLauncher
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class InputCodeFragment : ComposeFragment, FragmentTransition.LeftRight {
    private val launcher: InputCodeLauncher by launcherLazy()

    override val viewModel: InputCodeViewModel by viewModel(parameters = { parametersOf(launcher) })

    @Suppress("unused")
    @Deprecated("", level = DeprecationLevel.ERROR)
    constructor() : super()

    constructor(launcher: InputCodeLauncher) : super() {
        setLauncher(launcher)
    }

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        InputCodeContent(
            uiState = uiState,
            controller = viewModel,
        )
    }
}
package kz.cicada.berkut.feature.auth.presentation.name

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.compose.extension.collectAsStateWithLifecycle
import kz.cicada.berkut.lib.core.ui.extensions.launcherLazy
import kz.cicada.berkut.lib.core.ui.extensions.setLauncher
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class InputNameFragment : ComposeFragment, FragmentTransition.LeftRight {
    private val launcher: InputNameLauncher by launcherLazy()
    override val viewModel: InputNameViewModel by viewModel(parameters = { parametersOf(launcher) })

    @Suppress("unused")
    @Deprecated("", level = DeprecationLevel.ERROR)
    constructor() : super()

    constructor(launcher: InputNameLauncher) : super() {
        setLauncher(launcher)
    }

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        InputNameContent(
            uiState = uiState,
            controller = viewModel,
        )
    }
}
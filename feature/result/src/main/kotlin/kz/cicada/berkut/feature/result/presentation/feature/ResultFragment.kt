package kz.cicada.berkut.feature.result.presentation.feature

import androidx.compose.runtime.Composable
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.extensions.launcherLazy
import kz.cicada.berkut.lib.core.ui.extensions.setLauncher
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class ResultFragment : ComposeFragment, FragmentTransition.LeftRight {
    private val launcher: ResultLauncher by launcherLazy()
    override val viewModel: ResultViewModel by viewModel(parameters = { parametersOf(launcher) })

    @Suppress("unused")
    @Deprecated("", level = DeprecationLevel.ERROR)
    constructor() : super()

    constructor(launcher: ResultLauncher) : super() {
        setLauncher(launcher)
    }

    @Composable
    override fun Content() {
        ResultContent(
            uiState = viewModel.uiState,
            controller = viewModel,
        )
    }
}
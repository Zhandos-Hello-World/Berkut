package kz.cicada.berkut.feature.chooser.presentation.feature.simple.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import kz.cicada.berkut.feature.chooser.presentation.feature.simple.SimpleChooserLauncher
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeBottomSheetFragment
import kz.cicada.berkut.lib.core.ui.compose.extension.collectAsStateWithLifecycle
import kz.cicada.berkut.lib.core.ui.extensions.launcherLazy
import kz.cicada.berkut.lib.core.ui.extensions.setLauncher
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SimpleChooserFragment : ComposeBottomSheetFragment, FragmentTransition.BottomTop {
    private val launcher: SimpleChooserLauncher by launcherLazy()

    override val viewModel: SimpleChooserViewModel by viewModel(
        parameters = { parametersOf(launcher) }
    )

    @Suppress("unused")
    @Deprecated("", level = DeprecationLevel.ERROR)
    constructor() : super()

    constructor(launcher: SimpleChooserLauncher) : super() {
        setLauncher(launcher)
    }

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        SimpleChooserContent(
            uiState = uiState,
            controller = viewModel,
        )
    }
}
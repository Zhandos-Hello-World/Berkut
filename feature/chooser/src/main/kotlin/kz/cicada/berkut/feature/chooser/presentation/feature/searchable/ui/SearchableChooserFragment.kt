package kz.cicada.berkut.feature.chooser.presentation.feature.searchable.ui

import kz.cicada.berkut.feature.chooser.presentation.feature.searchable.SearchableChooserLauncher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.compose.extension.collectAsStateWithLifecycle
import kz.cicada.berkut.lib.core.ui.extensions.launcherLazy
import kz.cicada.berkut.lib.core.ui.extensions.setLauncher
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SearchableChooserFragment: ComposeFragment, FragmentTransition.LeftRight {
    private val launcher: SearchableChooserLauncher by launcherLazy()

    override val viewModel: SearchableChooserViewModel by viewModel(
        parameters = { parametersOf(launcher) }
    )

    @Suppress("unused")
    @Deprecated("", level = DeprecationLevel.ERROR)
    constructor() : super()

    constructor(launcher: SearchableChooserLauncher) : super() {
        setLauncher(launcher)
    }

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        SearchableChooserContent(
            uiState = uiState,
            controller = viewModel,
        )
    }
}
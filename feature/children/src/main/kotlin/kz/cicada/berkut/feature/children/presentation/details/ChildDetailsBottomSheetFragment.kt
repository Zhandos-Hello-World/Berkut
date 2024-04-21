package kz.cicada.berkut.feature.children.presentation.details

import androidx.compose.runtime.Composable
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeBottomSheetFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class ChildDetailsBottomSheetFragment(
    private val launcher: ChildDetailsLauncher,
) : ComposeBottomSheetFragment() {
    override val viewModel: ChildDetailsViewModel by viewModel(parameters = { parametersOf(launcher) })

    @Composable
    override fun Content() {

    }
}
package kz.cicada.berkut.feature.auth.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import kz.cicada.berkut.feature.auth.domain.model.UserType
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.compose.extension.collectAsStateWithLifecycle
import kz.cicada.berkut.lib.core.ui.extensions.launcherLazy
import kz.cicada.berkut.lib.core.ui.extensions.setLauncher
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

internal class LoginFragment : ComposeFragment, FragmentTransition.LeftRight {
    private val launcher: UserType by launcherLazy()

    override val viewModel: LoginViewModel by viewModel(parameters = { parametersOf(launcher) })

    @Suppress("unused")
    @Deprecated("", level = DeprecationLevel.ERROR)
    private constructor() : super()

    constructor(type: UserType) : super() {
        setLauncher(type)
    }

    @Composable
    override fun Content() {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        LoginContent(
            uiState = uiState,
            controller = viewModel,
        )
    }
}
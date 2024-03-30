package kz.cicada.berkut.feature.profile.presentation.profile

import androidx.compose.runtime.Composable
import kz.cicada.berkut.lib.core.ui.compose.base.ComposeFragment
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : ComposeFragment(), FragmentTransition.LeftRight {
    override val viewModel: ProfileViewModel by viewModel()

    @Composable
    override fun Content() {

    }
}
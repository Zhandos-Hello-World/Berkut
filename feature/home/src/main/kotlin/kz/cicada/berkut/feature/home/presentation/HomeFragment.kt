package kz.cicada.berkut.feature.home.presentation

import android.os.Bundle
import android.view.View
import kz.cicada.berkut.feature.home.R
import kz.cicada.berkut.feature.home.databinding.FragmentHomeBinding
import kz.cicada.berkut.lib.core.ui.base.fragment.BindingBaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BindingBaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override val viewModel: HomeViewModel by viewModel()

    override fun bindView(view: View) = FragmentHomeBinding.bind(view)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
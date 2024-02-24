package kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface BerkutDialogFragmentScreen : FragmentScreen, BerkutScreen {

    override fun createFragment(factory: FragmentFactory): DialogFragment

    companion object {
        operator fun invoke(
            key: String? = null,
            clearContainer: Boolean = true,
            fragmentCreator: Creator<FragmentFactory, DialogFragment>,
        ) = object : BerkutDialogFragmentScreen {
            override val screenKey = key ?: fragmentCreator::class.java.name
            override val clearContainer = clearContainer
            override fun createFragment(factory: FragmentFactory): DialogFragment =
                fragmentCreator.create(factory)
        }
    }
}
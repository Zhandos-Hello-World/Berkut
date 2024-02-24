package kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.Creator
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface BerkutFragmentScreen : FragmentScreen, BerkutScreen {

    companion object {
        operator fun invoke(
            key: String? = null,
            clearContainer: Boolean = true,
            fragmentCreator: Creator<FragmentFactory, Fragment>,
        ) = object : BerkutFragmentScreen {
            override val screenKey = key ?: fragmentCreator::class.java.name
            override val clearContainer = clearContainer
            override fun createFragment(factory: FragmentFactory): Fragment =
                fragmentCreator.create(factory)
        }
    }
}
package kz.cicada.berkut.lib.core.ui.navigation.cicerone

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.Forward
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kz.cicada.berkut.lib.core.castOrNull
import kz.cicada.berkut.lib.core.ui.navigation.FragmentTransition
import kz.cicada.berkut.lib.core.ui.navigation.cicerone.screen.BerkutDialogFragmentScreen

class BerkutNavigator(
    activity: FragmentActivity,
    containerId: Int,
    fragmentManager: FragmentManager = activity.supportFragmentManager,
    fragmentFactory: FragmentFactory = fragmentManager.fragmentFactory,
) : AppNavigator(
    activity = activity,
    containerId = containerId,
    fragmentManager = fragmentManager,
    fragmentFactory = fragmentFactory,
) {

    override fun setupFragmentTransaction(
        screen: FragmentScreen,
        fragmentTransaction: FragmentTransaction,
        currentFragment: Fragment?,
        nextFragment: Fragment,
    ) {
        if (nextFragment is FragmentTransition) {
            val transition = nextFragment.getTransitionAnimation()

            if (transition.popEnter == null || transition.popExit == null) {
                fragmentTransaction.setCustomAnimations(
                    transition.enter,
                    transition.exit,
                )
            } else {
                fragmentTransaction.setCustomAnimations(
                    transition.enter,
                    transition.exit,
                    transition.popEnter,
                    transition.popExit,
                )
            }
        }
        super.setupFragmentTransaction(
            screen,
            fragmentTransaction,
            currentFragment,
            nextFragment,
        )
        fragmentTransaction.setReorderingAllowed(true)
    }

    override fun forward(command: Forward) {
        if (command.screen.castOrNull<FragmentScreen>()?.clearContainer == true) {
            val dialogFragment = getLastDialogFragment()
            dialogFragment?.dismiss()
        }
        when (val screen = command.screen) {
            is BerkutDialogFragmentScreen -> showDialogFragment(screen)
            else -> super.forward(command)
        }
    }

    override fun back() {
        val dialogFragment = getLastDialogFragment()
        if (dialogFragment != null) {
            dialogFragment.dismiss()
        } else {
            super.back()
        }
    }

    private fun showDialogFragment(screen: BerkutDialogFragmentScreen) {
        screen.createFragment(fragmentFactory).show(fragmentManager, screen.screenKey)
    }

    private fun getLastDialogFragment(): DialogFragment? {
        return fragmentManager.fragments.lastOrNull().castOrNull<DialogFragment>()
    }
}
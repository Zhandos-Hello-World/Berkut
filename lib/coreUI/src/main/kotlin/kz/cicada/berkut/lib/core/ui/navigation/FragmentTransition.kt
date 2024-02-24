package kz.cicada.berkut.lib.core.ui.navigation

import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import kz.cicada.berkut.core.presentation.R

sealed interface FragmentTransition {

    fun getTransitionAnimation(): Transition

    class Transition(
        @AnimatorRes @AnimRes
        val enter: Int,
        @AnimatorRes @AnimRes
        val exit: Int,
        @AnimatorRes @AnimRes
        val popEnter: Int? = null,
        @AnimatorRes @AnimRes
        val popExit: Int? = null,
    )

    interface Slide : FragmentTransition {
        override fun getTransitionAnimation() = Transition(
            R.anim.slide_in_left,
            R.anim.slide_out_right,
            R.anim.slide_in_right,
            R.anim.slide_out_left,
        )
    }

    interface Fade : FragmentTransition {
        override fun getTransitionAnimation() = Transition(
            R.anim.anim_fade_in,
            R.anim.anim_fade_out,
            R.anim.anim_fade_in,
            R.anim.anim_fade_out,
        )
    }

    interface BottomTop : FragmentTransition {
        override fun getTransitionAnimation() = Transition(
            R.anim.enter_from_bottom,
            R.anim.anim_stay,
            R.anim.anim_stay,
            R.anim.exit_to_bottom,
        )
    }

    interface LeftRight : FragmentTransition {
        override fun getTransitionAnimation() = Transition(
            R.anim.enter_from_right,
            R.anim.exit_to_left,
            R.anim.enter_from_left,
            R.anim.exit_to_right,
        )
    }
}
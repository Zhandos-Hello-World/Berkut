package kz.cicada.berkut.lib.core.ui.compose.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import kz.cicada.berkut.lib.core.ui.base.dialog.BaseBottomSheetDialogFragment
import kz.cicada.berkut.lib.core.ui.compose.theme.AppTheme

abstract class ComposeBottomSheetFragment(
    fullScreen: Boolean = false,
    useAdjustResize: Boolean = false,
) : BaseBottomSheetDialogFragment(
    layoutId = 0,
    fullScreen = fullScreen,
    useAdjustResize = useAdjustResize,
) {

    @Composable
    abstract fun Content()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = ComposeView(requireContext()).apply {
        id = View.generateViewId()
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT,
        )
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnDetachedFromWindow)
        setContent {
            AppTheme {
                this@ComposeBottomSheetFragment.Content()
            }
        }
    }
}
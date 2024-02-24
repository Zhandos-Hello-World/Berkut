package kz.cicada.berkut.lib.core.ui.base.dialog

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.WindowManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kz.cicada.berkut.lib.core.ui.extensions.dp

private const val DEFAULT_TOP_MARGIN = 56

abstract class ScalableBottomSheetDialogFragment : BottomSheetDialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)?.let {
                BottomSheetBehavior.from(it).state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    protected fun fullScreenSize(
        customView: View,
        marginTopDp: Int = DEFAULT_TOP_MARGIN,
    ): BottomSheetDialogFragment {
        val screenDimension = requireActivity().getScreenDimensions()

        BottomSheetBehavior.from(customView.parent as View).also { beh ->
            val marginTopInPx = marginTopDp.dp
            val targetHeight = screenDimension.y - marginTopInPx
            beh.peekHeight = targetHeight
            customView.viewTreeObserver.addOnGlobalLayoutListener(
                object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        customView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                        (customView.parent as View).layoutParams.height = targetHeight
                        customView.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
                        (customView.parent as View).requestLayout()
                    }
                },
            )
        }

        return this
    }

    /**
     * Временная копипаста с ActivityExt.kt до полного его вынесения в coreUi
     */
    protected fun Activity.getScreenDimensions(): Point {
        val display = (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }
}
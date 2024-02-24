package kz.cicada.berkut.lib.core.ui.base.dialog

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

abstract class BindingBaseBottomSheetDialogFragment<T : ViewBinding>(
    @LayoutRes private val layoutId: Int,
    fullScreen: Boolean = false,
    useAdjustResize: Boolean = false,
) : BaseBottomSheetDialogFragment(layoutId, fullScreen, useAdjustResize) {

    private var _viewBinding: T? = null
    protected val viewBinding: T
        get() = _viewBinding ?: throw RuntimeException("ViewBinding is null")

    abstract fun bindView(view: View): T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewBinding = bindView(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}
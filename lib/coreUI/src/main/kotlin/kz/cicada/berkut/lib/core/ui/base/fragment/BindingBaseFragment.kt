package kz.cicada.berkut.lib.core.ui.base.fragment

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding

abstract class BindingBaseFragment<T : ViewBinding>(
    @LayoutRes private val layoutId: Int,
) : BaseFragment(layoutId) {

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
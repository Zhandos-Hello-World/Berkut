package kz.cicada.berkut.lib.core.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import kz.cicada.berkut.core.presentation.R
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.extensions.addOnBackPressedDispatcher
import kz.cicada.berkut.lib.core.ui.extensions.hideKeyboard
import kz.cicada.berkut.lib.core.ui.extensions.setOnMenuItemSafeClickListener

abstract class BaseFragment(
    @LayoutRes private val layoutId: Int,
) : Fragment(), FragmentDelegate by DefaultFragmentDelegate() {

    @MenuRes
    open var toolbarMenuId: Int? = null
    open var menuItemClickLister: Toolbar.OnMenuItemClickListener? = null
    protected var toolbarParent: Toolbar? = null
    abstract val viewModel: BaseViewModel?

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBackPressed()?.let {
            addOnBackPressedDispatcher { it() }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = inflater.inflate(layoutId, container, false)

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        registerFragment(this, viewModel)
    }

    fun setTitle(@StringRes stringId: Int) {
        setTitle(getString(stringId))
    }

    fun setTitle(title: CharSequence) {
        toolbarParent?.post {
            toolbarParent?.title = title
        }
    }

    private fun initToolbar() {
        view?.findViewById<Toolbar>(R.id.toolbar_parent)?.apply {
            toolbarParent = this
            setNavigationIcon(R.drawable.ic_back)
            (requireActivity() as AppCompatActivity).setSupportActionBar(this)

            toolbarMenuId?.let { inflateMenu(it) }
            menuItemClickLister?.let { setOnMenuItemSafeClickListener(menuItemClickLister) }
            setNavigationOnClickListener {
                hideKeyboard()
                activity?.onBackPressed()
            }
        }
    }
}
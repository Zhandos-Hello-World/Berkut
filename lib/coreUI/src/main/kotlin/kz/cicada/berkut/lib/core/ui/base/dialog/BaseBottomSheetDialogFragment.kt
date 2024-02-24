package kz.cicada.berkut.lib.core.ui.base.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kz.cicada.berkut.core.presentation.R
import kz.cicada.berkut.lib.core.ui.base.fragment.DefaultFragmentDelegate
import kz.cicada.berkut.lib.core.ui.base.fragment.FragmentDelegate

/**
 * Базовый BottomSheet диалог
 * чтоб собрать передать лэйаут
 * @param [useAdjustResize] - поведение лэйаута при открытии клавиатуры
 * или при др изменинии размера экрана
 */
abstract class BaseBottomSheetDialogFragment(
    @LayoutRes private val layoutId: Int,
    private val fullScreen: Boolean = false,
    private val useAdjustResize: Boolean = false,
) : ScalableBottomSheetDialogFragment(), FragmentDelegate by DefaultFragmentDelegate() {

    private var initialHeight = 0
    protected var toolbar: MaterialToolbar? = null
    abstract val viewModel: kz.cicada.berkut.lib.core.ui.base.BaseViewModel?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (useAdjustResize) {
            setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomSheetStyle)
        }
    }

    /**
     * Желательно тут не привязывать вьюхи из за биндинга может
     * не правильно взять parent для вьюхи
     * использовать [onCreateView]
     */
    @CallSuper
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        dialog.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                onBackPressed()()
                true
            } else {
                false
            }
        }

        dialog.setOnShowListener {
            dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)?.let {
                BottomSheetBehavior.from(it).state = BottomSheetBehavior.STATE_EXPANDED
                initialHeight = it.height
            }
        }

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerFragment(this, viewModel)
        if (fullScreen) fullScreenSize(view, 0)

        toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)?.also {
            it.setNavigationOnClickListener { onNavigationIconClicked() }
        }
    }

    open fun onNavigationIconClicked() {
        dismiss()
    }

    override fun onBackPressed(): (() -> Unit) = {
        dismiss()
    }

    fun setTitle(@StringRes titleId: Int) {
        setTitle(getString(titleId))
    }

    fun setTitle(title: String) {
        toolbar?.title = title
    }
}
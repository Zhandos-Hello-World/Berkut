package kz.cicada.berkut.lib.core.ui.base.fragment

import android.app.Dialog
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kz.cicada.berkut.core.presentation.R
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.event.ActionEvent
import kz.cicada.berkut.lib.core.ui.event.BlockingLoaderEvent
import kz.cicada.berkut.lib.core.ui.event.EventCloseKeyboard
import kz.cicada.berkut.lib.core.ui.event.EventHandler
import kz.cicada.berkut.lib.core.ui.event.EventHandlerHelper
import kz.cicada.berkut.lib.core.ui.event.GetFragmentEvent
import kz.cicada.berkut.lib.core.ui.event.ShowAlertDialogEvent
import kz.cicada.berkut.lib.core.ui.event.ShowSnackBarEvent
import kz.cicada.berkut.lib.core.ui.event.ShowSnackBarStringEvent
import kz.cicada.berkut.lib.core.ui.event.ShowSnackBarUiTextEvent
import kz.cicada.berkut.lib.core.ui.event.ShowToastEvent
import kz.cicada.berkut.lib.core.ui.event.ShowToastEventText
import kz.cicada.berkut.lib.core.ui.event.GetFragmentActivityEvent
import kz.cicada.berkut.lib.core.ui.extensions.hideKeyboard

interface FragmentDelegate : EventHandler {
    fun registerFragment(fragment: Fragment, baseViewModel: BaseViewModel?)
    fun showBlockingLoader()
    fun hideBlockingLoader()
    fun showSnackbar(@StringRes stringId: Int)
    fun showSnackbar(string: String)
    fun showToast(@StringRes stringId: Int)
    fun showToast(text: String)
    fun showAlertDialog(
        title: String?,
        message: String?,
        positiveButton: String?,
        positiveButtonClick: (() -> Unit)?,
        negativeButton: String? = null,
        negativeButtonClick: (() -> Unit)? = null,
        cancelable: Boolean? = null,
    )
}

class DefaultFragmentDelegate : FragmentDelegate, DefaultLifecycleObserver {

    private lateinit var fragment: Fragment
    private val helper = EventHandlerHelper()
    private var blockingDialog: Dialog? = null
    private val activity get() = fragment.activity

    override fun registerFragment(fragment: Fragment, baseViewModel: BaseViewModel?) {
        this.fragment = fragment
        fragment.viewLifecycleOwner.lifecycle.addObserver(this)
        helper.observeViewModel(
            fragment as EventHandler,
            fragment.viewLifecycleOwner,
            baseViewModel,
        )
        initBlockingDialog()
    }

    @CallSuper
    override fun onActionEvent(event: ActionEvent) {
        with(fragment) {
            when (event) {
                is EventCloseKeyboard -> hideKeyboard()
                is ShowSnackBarEvent -> showSnackbar(event.id)
                is ShowSnackBarStringEvent -> showSnackbar(event.text)
                is ShowSnackBarUiTextEvent -> showSnackbar(event.text.toString(requireContext()))
                is ShowToastEvent -> showToast(event.id)
                is ShowToastEventText -> showToast(event.text)
                is BlockingLoaderEvent -> {
                    if (event.show) {
                        showBlockingLoader()
                    } else {
                        hideBlockingLoader()
                    }
                }
                is ShowAlertDialogEvent -> {
                    showAlertDialog(
                        title = event.title?.toString(requireContext()),
                        message = event.message?.toString(requireContext()),
                        positiveButton = event.positiveButton?.toString(requireContext()),
                        positiveButtonClick = event.positiveButtonClick,
                        negativeButton = event.negativeButton?.toString(requireContext()),
                        negativeButtonClick = event.negativeButtonClick,
                        cancelable = event.cancellable,
                    )
                }
                is GetFragmentActivityEvent -> event.action.invoke(fragment.requireActivity())
                is GetFragmentEvent -> event.action.invoke(fragment)
            }
        }
    }

    override fun onBackPressed(): (() -> Unit)? = null

    override fun showBlockingLoader() {
        if (activity?.isFinishing == false) {
            blockingDialog?.show()
        }
    }

    override fun hideBlockingLoader() {
        if (activity?.isFinishing == false) {
            blockingDialog?.dismiss()
        }
    }

    override fun showSnackbar(@StringRes stringId: Int) {
        showSnackbar(fragment.getString(stringId))
    }

    override fun showSnackbar(string: String) {
        Snackbar.make(
            fragment.requireActivity().findViewById(R.id.coordinator),
            string,
            Snackbar.LENGTH_LONG,
        ).setBackgroundTint(
            ContextCompat.getColor(fragment.requireContext(), R.color.black80),
        ).apply {
            view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).maxLines = 3
            show()
        }
    }

    override fun showToast(@StringRes stringId: Int) {
        showToast(fragment.getString(stringId))
    }

    override fun showToast(text: String) {
        Toast.makeText(fragment.requireContext(), text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        hideBlockingLoader()
        blockingDialog = null
    }

    override fun showAlertDialog(
        title: String?,
        message: String?,
        positiveButton: String?,
        positiveButtonClick: (() -> Unit)?,
        negativeButton: String?,
        negativeButtonClick: (() -> Unit)?,
        cancelable: Boolean?,
    ) {
        MaterialAlertDialogBuilder(fragment.requireContext(), R.style.AlertDialogTheme)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(cancelable ?: true)
            .setPositiveButton(positiveButton) { dialogInterface, _ ->
                positiveButtonClick?.invoke()
                dialogInterface.dismiss()
            }
            .setNegativeButton(negativeButton) { dialogInterface, _ ->
                negativeButtonClick?.invoke()
                dialogInterface.dismiss()
            }
            .show()
    }

    private fun initBlockingDialog() {
        val view = fragment.layoutInflater.inflate(R.layout.layout_progress_logo, null)
        blockingDialog = Dialog(fragment.requireContext(), R.style.ProgressDialogBlocking).apply {
            setContentView(view)
            setCancelable(false)
        }
    }
}
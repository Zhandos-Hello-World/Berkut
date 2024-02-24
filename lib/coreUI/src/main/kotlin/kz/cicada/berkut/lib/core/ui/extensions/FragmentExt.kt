package kz.cicada.berkut.lib.core.ui.extensions

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.os.Parcelable
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.annotation.MainThread
import androidx.core.content.FileProvider
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kz.cicada.berkut.core.presentation.R
import kz.cicada.berkut.lib.core.ui.navigation.LauncherLazy
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

private const val FRAGMENT_LAUNCHER = "launcher"

fun Fragment.showAlertDialogIntRes(
    title: Int? = null,
    message: Int? = null,
    positiveClick: DialogInterface.OnClickListener? = null,
    isCancelable: Boolean = true,
) {
    showAlertDialog(
        title = if (title != null) getString(title) else null,
        message = if (message != null) getString(message) else null,
        positiveClick = positiveClick,
        isCancelable = isCancelable,
    )
}

fun Fragment.showAlertDialog(
    title: String? = null,
    message: String? = null,
    positiveClick: DialogInterface.OnClickListener? = null,
    isCancelable: Boolean = true,
    positiveButton: String = getString(R.string.ok),
    negativeButton: String? = null,
    negativeClick: DialogInterface.OnClickListener? = null,
) {
    MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
        .setTitle(title)
        .setMessage(message)
        .setCancelable(isCancelable)
        .setPositiveButton(positiveButton, positiveClick)
        .setNegativeButton(negativeButton, negativeClick)
        .show()
}

private fun getFragmentStringOrNull(fm: FragmentManager, current: Boolean): String? {
    fun getFragments(fm: FragmentManager): List<Fragment> {
        return fm.fragments.filter { it?.isAdded ?: false }.flatMap { fragment ->
            listOf(fragment) + fragment.host?.let { getFragments(fragment.childFragmentManager) }
                .orEmpty()
        }
    }

    val fragments = getFragments(fm)
    val count = fragments.size
    val fragment = fragments.getOrNull(count - if (current) 1 else 2)
    val key = fragment?.let { it::class.java.simpleName }
    return key
}

fun Fragment.observeNavigationResult(
    lifecycleOwner: LifecycleOwner = viewLifecycleOwner,
    key: String? = null,
    callback: (Any) -> Unit,
) {
    val requestKey = key ?: getFragmentStringOrNull(parentFragmentManager, true)
    requestKey?.also {
        requireActivity().supportFragmentManager.setFragmentResultListener(
            requestKey,
            lifecycleOwner,
        ) { _, bundle ->
            bundle.get(requestKey)?.also(callback)
        }
    }
}

fun Fragment.postNavigationResult(result: Any, key: String? = null) {
    val requestKey = key ?: getFragmentStringOrNull(parentFragmentManager, false)
    requestKey?.also {
        requireActivity().supportFragmentManager.setFragmentResult(
            requestKey,
            bundleOf(requestKey to result),
        )
    }
}

fun Fragment.postCurrentNavigationResult(result: Any, key: String? = null) {
    val requestKey = key ?: getFragmentStringOrNull(parentFragmentManager, true)
    postNavigationResult(result, requestKey)
}

fun Fragment.hideKeyboard() {
    try {
        val inputMethodManager: InputMethodManager? =
            context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        view?.windowToken?.let {
            inputMethodManager?.hideSoftInputFromWindow(it, 0)
        }
    } catch (e: Throwable) {
    }
}

fun <T> Fragment.observe(liveData: LiveData<T>?, onUpdate: (T) -> Unit) {
    liveData?.observe(viewLifecycleOwner, Observer(onUpdate))
}

fun Fragment.addOnBackPressedDispatcher(unit: () -> Unit) {
    activity?.onBackPressedDispatcher?.addCallback(
        this,
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                unit()
            }
        },
    )
}

fun Fragment.shareFile(fileName: String, bytes: ByteArray) {
    try {
        val myExternalFile = File(
            requireContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),
            fileName,
        )

        FileOutputStream(myExternalFile).apply {
            write(bytes)
            close()
        }

        val fileUri = FileProvider.getUriForFile(
            requireContext(),
            requireContext().packageName + ".appfileprovider",
            myExternalFile,
        )

        val shareIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "application/octet-stream"
            putExtra(Intent.EXTRA_STREAM, fileUri)
        }

        val chooser = Intent.createChooser(shareIntent, null)
        activity?.packageManager?.queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY)
            ?.forEach {
                activity?.grantUriPermission(
                    it.activityInfo.packageName,
                    fileUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION,
                )
            }

        startActivity(chooser)
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun Fragment.setLauncher(launcher: Parcelable): Fragment {
    arguments = bundleOf(FRAGMENT_LAUNCHER to launcher)
    return this
}

fun <T : Any> Fragment.getLauncher(): T {
    return arguments.getLauncher()
}

fun <T : Any> Bundle?.getLauncher(): T {
    return this?.getParcelable(FRAGMENT_LAUNCHER) as? T
        ?: this?.getSerializable(FRAGMENT_LAUNCHER) as? T
        ?: throw RuntimeException("Launcher can not be null")
}

/**
 * Returns a [Lazy] delegate to access the Fragment's arguments as an [Args] instance.
 *
 * It is strongly recommended that this method only be used when the Fragment is created
 * by Fragment with the corresponding object, which ensures that the required
 * arguments are present.
 *
 * ```
 * class MyFragment : Fragment() {
 *     private val launcher: MyFragmentLauncher by launcherLazy()
 * }
 * ```
 *
 * This property can be accessed only after the Fragment's constructor.
 */
@MainThread
inline fun <reified Args : Any> Fragment.launcherLazy() = LauncherLazy<Args> {
    arguments ?: throw IllegalStateException("Fragment $this has null arguments")
}
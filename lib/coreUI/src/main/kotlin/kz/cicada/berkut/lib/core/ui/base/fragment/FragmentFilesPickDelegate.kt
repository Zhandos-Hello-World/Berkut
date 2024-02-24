package kz.cicada.berkut.lib.core.ui.base.fragment

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kz.cicada.berkut.core.presentation.R
import kz.cicada.berkut.lib.core.ui.permissions.PermissionListener
import kz.cicada.berkut.lib.core.ui.permissions.Permissions
import kz.cicada.berkut.lib.core.ui.utils.DeviceUtils

interface FragmentFilesPickDelegate {
    fun registerFragmentFilesPickDelegate(fragment: Fragment, callback: (List<Uri>) -> Unit)
    fun checkStoragePermissionAndOpenPicker()
}

class DefaultFragmentFilesPickDelegate(
    private val allowMultiple: Boolean = false,
    private vararg val mimeTypes: String = arrayOf("*/*"),
) : FragmentFilesPickDelegate, DefaultLifecycleObserver {

    private lateinit var fragment: Fragment
    private lateinit var filesLauncher: ActivityResultLauncher<Intent>

    override fun registerFragmentFilesPickDelegate(
        fragment: Fragment,
        callback: (List<Uri>) -> Unit,
    ) {
        this.fragment = fragment
        filesLauncher = fragment.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.let { intent ->
                    val uriList = arrayListOf<Uri>()
                    if (intent.clipData != null) {
                        for (i in 0 until intent.clipData!!.itemCount) {
                            uriList.add(intent.clipData!!.getItemAt(i).uri)
                        }
                    } else {
                        intent.data?.let { uriList.add(it) }
                    }
                    callback.invoke(uriList)
                }
            }
        }
    }

    override fun checkStoragePermissionAndOpenPicker() {
        val permission = when {
            Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU -> Manifest.permission.WRITE_EXTERNAL_STORAGE
            else -> Manifest.permission.READ_MEDIA_IMAGES
        }
        Permissions()
            .init(fragment)
            .permissions(permission)
            .setListener(
                object : PermissionListener {
                    override fun onPermissionGranted() {
                        openFilePicker()
                    }

                    override fun onPermissionDenied(
                        deniedPermissions: List<String>,
                        showReasonForPermissions: List<String>,
                    ) {
                        showAlertWhenPermissionIsDenied()
                    }
                },
            )
            .check()
    }

    private fun openFilePicker() {
        try {
            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                putExtra(Intent.EXTRA_ALLOW_MULTIPLE, allowMultiple)
                type = mimeTypes.joinToString(separator = " ")
                addCategory(Intent.CATEGORY_OPENABLE)
                addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
                putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
            }

            filesLauncher.launch(
                Intent.createChooser(
                    intent,
                    fragment.getString(R.string.select_file),
                ),
            )
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(
                fragment.requireContext(),
                fragment.getString(R.string.install_file_manager),
                Toast.LENGTH_SHORT,
            ).show()
        }
    }

    private fun showAlertWhenPermissionIsDenied() {
        AlertDialog.Builder(fragment.requireContext())
            .setTitle(R.string.common_permission_denied_title)
            .setMessage(R.string.storage_permission_denied_message)
            .setCancelable(false)
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(R.string.settings) { _, _ ->
                DeviceUtils.goToAppSettings(fragment.requireContext())
            }.show()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        fragment.requireContext().cacheDir.deleteRecursively()
    }
}
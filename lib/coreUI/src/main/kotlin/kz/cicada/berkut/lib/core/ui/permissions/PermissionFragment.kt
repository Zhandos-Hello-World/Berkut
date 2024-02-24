package kz.cicada.berkut.lib.core.ui.permissions

import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment

class PermissionFragment : Fragment() {

    private var builder: PermissionBuilder? = null

    private var permissionsLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
    ) { permissions ->
        if (permissions.values.none { !it }) {
            builder?.permissionListener?.onPermissionGranted()
        } else {
            val deniedPermissions = permissions.filter { !it.value }.map { it.key }

            val showReasonList = arrayListOf<String>()
            deniedPermissions.forEach {
                val shouldShowRationale = shouldShowRequestPermissionRationale(it)
                if (shouldShowRationale) {
                    showReasonList.add(it)
                }
            }

            builder?.permissionListener?.onPermissionDenied(deniedPermissions, showReasonList)
        }

        builder?.endRequest()
    }

    fun checkPermissions(permissionBuilder: PermissionBuilder, permissions: List<String>) {
        builder = permissionBuilder
        permissionsLauncher.launch(permissions.toTypedArray())
    }
}
package kz.cicada.berkut.lib.core.ui.permissions

interface PermissionListener {

    fun onPermissionGranted()

    fun onPermissionDenied(deniedPermissions: List<String>, showReasonForPermissions: List<String>)
}
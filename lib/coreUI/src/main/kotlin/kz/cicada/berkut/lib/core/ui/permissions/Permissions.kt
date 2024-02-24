package kz.cicada.berkut.lib.core.ui.permissions

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class Permissions {

    fun init(fragment: Fragment): PermissionBuilder {
        return PermissionBuilder(fragment)
    }

    fun isGranted(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission,
        ) == PackageManager.PERMISSION_GRANTED
    }
}
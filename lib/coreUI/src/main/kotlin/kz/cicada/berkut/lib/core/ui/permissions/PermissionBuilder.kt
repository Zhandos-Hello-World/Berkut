package kz.cicada.berkut.lib.core.ui.permissions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class PermissionBuilder(fragment: Fragment) {

    companion object {
        private const val FRAGMENT_TAG = "PermissionFragmentTag"
        private var inRequestFlow = false
    }

    private val fragmentManager: FragmentManager = fragment.childFragmentManager

    private val permissionFragment: PermissionFragment?
        get() {
            val existedFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG)
            return if (existedFragment != null) {
                existedFragment as PermissionFragment
            } else {
                getNewPermissionFragment()
            }
        }

    @JvmField
    internal var permissions: List<String> = emptyList()

    @JvmField
    internal var permissionListener: PermissionListener? = null

    fun permissions(vararg permissions: String): PermissionBuilder {
        this.permissions = listOf(*permissions)
        return this
    }

    fun setListener(listener: PermissionListener?): PermissionBuilder {
        permissionListener = listener
        return this
    }

    fun check() {
        if (inRequestFlow) return
        inRequestFlow = permissionFragment != null
        permissionFragment?.checkPermissions(this, permissions)
    }

    internal fun endRequest() {
        val existedFragment = fragmentManager.findFragmentByTag(FRAGMENT_TAG)
        existedFragment?.let {
            removePermissionFragment(it)
        }
        inRequestFlow = false
    }

    private fun getNewPermissionFragment(): PermissionFragment? {
        return try {
            val invisibleFragment = PermissionFragment()
            fragmentManager.beginTransaction()
                .add(invisibleFragment, FRAGMENT_TAG)
                .commitNowAllowingStateLoss()
            invisibleFragment
        } catch (e: Throwable) {
            e.printStackTrace()
            null
        }
    }

    private fun removePermissionFragment(fragment: Fragment) {
        try {
            fragmentManager
                .beginTransaction()
                .remove(fragment)
                .commitNowAllowingStateLoss()
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
}
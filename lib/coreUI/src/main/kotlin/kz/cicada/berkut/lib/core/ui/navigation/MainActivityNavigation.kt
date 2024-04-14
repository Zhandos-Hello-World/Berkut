package kz.cicada.berkut.lib.core.ui.navigation

interface MainActivityNavigation {
    fun openMainFlow()
    fun openAuthFlow(logOut: Boolean = false)
    fun openMapTab()
    fun openChildTab()
    fun openHomeTab()
    fun openLocationsTab()
}
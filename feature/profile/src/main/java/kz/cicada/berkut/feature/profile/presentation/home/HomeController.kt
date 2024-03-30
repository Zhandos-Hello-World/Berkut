package kz.cicada.berkut.feature.profile.presentation.home

interface HomeController {
    fun navigateToHotlineNumbers()
    fun navigateToScanQr()
    fun navigateToShareQR()
    fun navigateUp()
    fun navigateToPrivacy()
    fun navigateToSupport()
    fun navigateToAddTask()
    fun navigateToAddSaveLocations()
    fun onLogoutClick()
}
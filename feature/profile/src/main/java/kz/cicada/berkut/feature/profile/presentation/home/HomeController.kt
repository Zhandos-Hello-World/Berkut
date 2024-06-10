package kz.cicada.berkut.feature.profile.presentation.home

interface HomeController {
    fun navigateToScanQr()
    fun navigateToShareQR()
    fun navigateUp()
    fun navigateToPrivacy()
    fun navigateToSupport()
    fun navigateToAddTask()
    fun navigateToAddSaveLocations()
    fun navigateToFAQ()
    fun onLogoutClick()
}
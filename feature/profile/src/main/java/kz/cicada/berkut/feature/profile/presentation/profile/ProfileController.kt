package kz.cicada.berkut.feature.profile.presentation.profile

interface ProfileController {
    fun onNavigateBack()
    fun changeUsername(username: String)
    fun changeProfile()
    fun onAddAvatarButtonIconClick()
}
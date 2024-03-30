package kz.cicada.berkut.feature.profile.presentation.hotline

interface AddHotlineNumberController {
    fun addHotlineNumber()
    fun inputNameOfHotline(name: String)
    fun inputPhoneNumber(phoneNumber: String)
    fun onNavigateBack()
}
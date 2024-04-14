package kz.cicada.berkut.feature.sos.presentation.hotline

interface AddHotlineNumberController {
    fun addHotlineNumber()
    fun inputNameOfHotline(name: String)
    fun inputPhoneNumber(phoneNumber: String)
    fun onNavigateBack()
}
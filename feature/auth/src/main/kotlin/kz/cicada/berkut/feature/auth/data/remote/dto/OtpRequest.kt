package kz.cicada.berkut.feature.auth.data.remote.dto

internal class OtpRequest(
    phoneNumber: String,
) : HashMap<String, String>() {

    init {
        put(PHONE_NUMBER, phoneNumber)
    }

    companion object {
        const val PHONE_NUMBER = "phone_number"
    }
}
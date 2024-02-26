package kz.cicada.berkut.feature.auth.data.remote.dto

class VerifyPhoneRequest(
    code: String,
    phoneNumber: String,
    username: String,
) : HashMap<String, String?>() {

    init {
        put(CODE, code)
        put(PHONE_NUMBER, phoneNumber)
        put(USERNAME, username)
    }

    companion object {
        private const val CODE = "code"
        private const val PHONE_NUMBER = "phone_number"
        private const val USERNAME = "username"
    }
}
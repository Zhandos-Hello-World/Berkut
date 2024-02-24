package am.strongte.hub.auth.data.remote.dto

class RegisterUserRequest(
    code: String,
    phoneNumber: String,
    username: String,
    userType: String,
    password: String,
): HashMap<String, String>() {

    init {
        put(CODE, code)
        put(PHONE_NUMBER, phoneNumber)
        put(USERNAME, username)
        put(USER_TYPE, userType)
        put(PASSWORD, password)
    }

    companion object {
        private const val CODE = "code"
        private const val PHONE_NUMBER = "phone_number"
        private const val USER_TYPE = "user_type"
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
    }
}
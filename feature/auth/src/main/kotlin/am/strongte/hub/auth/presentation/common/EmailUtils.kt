package am.strongte.hub.auth.presentation.common

private const val EMAIL_REGEX = "^[A-Za-z.]+@strongte\\.am$"

fun isValidEmail(email: String): Boolean =
    email.isNotBlank() && Regex(EMAIL_REGEX).matchEntire(email) != null
package kz.cicada.berkut.feature.auth.presentation.common

enum class ProfileField {
    NAME,
    SURNAME,
    EMAIL,
    DATE_OF_BIRTH,
    ADDRESS,
    JOB_POSITION,
    START_DATE,
    PHONE_NUMBER,
    TELEGRAM_LINK,
    NONE,
}

private const val MIN_PERSON_AGE = 17L
private const val ONE_DAY = 1L
private const val COMPANY_REGISTRATION_DATE = "26.07.2016"
const val KZ_OR_RU_PHONE_LENGTH = 11
const val PHONE_MAX_LENGTH = 15
const val KZ_OR_RU_COUNTRY_PHONE_CODE = "7"
const val TELEGRAM_LINK_PREFIX = "@"
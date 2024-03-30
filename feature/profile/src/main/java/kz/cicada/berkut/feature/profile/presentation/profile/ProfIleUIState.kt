package kz.cicada.berkut.feature.profile.presentation.profile

sealed interface ProfIleUIState {
    data object NotInit : ProfIleUIState
    data class Data(
        val username: String,
        val phoneNumber: String,
    )
}
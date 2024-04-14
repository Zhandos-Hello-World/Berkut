package kz.cicada.berkut.feature.profile.presentation.profile

sealed interface ProfIleUIState {
    data class Data(
        val userId: Int,
        val username: String,
        val phoneNumber: String,
        val loading: Boolean,
        val enabled: Boolean,
    ) : ProfIleUIState
}
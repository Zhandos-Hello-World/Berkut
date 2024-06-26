package kz.cicada.berkut.feature.children.presentation.details

sealed interface ChildDetailsUIState {

    data class Data(
        val imageUrl: String?,
        val username: String,
        val location: String,
        val battery: String,
    ) : ChildDetailsUIState

    data object Loading: ChildDetailsUIState
}
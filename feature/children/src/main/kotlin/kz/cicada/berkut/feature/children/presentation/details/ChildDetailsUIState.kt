package kz.cicada.berkut.feature.children.presentation.details

sealed interface ChildDetailsUIState {

    data class Data(
        val imageUrl: String,
        val username: String,
    ) : ChildDetailsUIState
}
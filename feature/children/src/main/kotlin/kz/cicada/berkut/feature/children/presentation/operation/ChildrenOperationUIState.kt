package kz.cicada.berkut.feature.children.presentation.operation

sealed class ChildrenOperationUIState {
    data class Data(
        val name: String, val phoneNumber: String, val imageUrl: String, val coins: String
    ) : ChildrenOperationUIState()

    data object Loading : ChildrenOperationUIState()
}
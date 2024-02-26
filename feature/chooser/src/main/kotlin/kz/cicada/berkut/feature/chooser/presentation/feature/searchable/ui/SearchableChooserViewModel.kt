package kz.cicada.berkut.feature.chooser.presentation.feature.searchable.ui

import kz.cicada.berkut.feature.chooser.presentation.feature.searchable.SearchableChooserLauncher
import kz.cicada.berkut.feature.chooser.presentation.model.ChooserDvo
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.cicada.berkut.lib.core.empty
import kz.cicada.berkut.lib.core.ui.base.BaseViewModel
import kz.cicada.berkut.lib.core.ui.base.LoadingType
import kz.cicada.berkut.lib.core.ui.base.ViewState
import kz.cicada.berkut.lib.core.ui.compose.extension.tryToUpdate

class SearchableChooserViewModel(private val launcher: SearchableChooserLauncher) :
    BaseViewModel(), SearchableChooserController {
    private var cachedSearchText: String = String.empty
    private val _uiState: MutableStateFlow<ViewState<SearchableChooserUiState>> =
        MutableStateFlow(ViewState.Loading(LoadingType.Progress))
    val uiState: StateFlow<ViewState<SearchableChooserUiState>> = _uiState
    private val searchQueryState: MutableSharedFlow<String> = MutableSharedFlow()

    init {
        initData()
        subscribeToSearchQueryChanges()
    }

    override fun searchTextChanged(text: String) {
        getCurrentUiStateData()?.let { data ->
            cachedSearchText = text
            viewModelScope.launch {
                _uiState.tryToUpdate {
                    ViewState.Data(
                        data.copy(
                            searchQuery = cachedSearchText,
                        ),
                    )
                }
                searchQueryState.emit(cachedSearchText)
            }
        }
    }

    override fun onSelectableClick(item: ChooserDvo.SelectableText) {
        val item = item.copy(isSelected = true)
    }

    private fun initData() {
        _uiState.value = ViewState.Loading(LoadingType.Progress)
        viewModelScope.launch {
            _uiState.value = with(launcher.behavior) {
                cachedSearchText = getInitialSearchQuery()
                ViewState.Data(
                    SearchableChooserUiState.Data(
                        searchQuery = getInitialSearchQuery(),
                        searchHint = getSearchHint(),
                        chooserItems = getItemList(),
                        emptyStateDetails = getEmptyStateDetails(),
                    ),
                )
            }
        }
    }

    private fun subscribeToSearchQueryChanges() {
        searchQueryState
            .filter { it.isNotEmpty() }
            .distinctUntilChanged()
            .debounce(500L)
            .flatMapLatest { flow { emit(search()) } }
            .onEach { data ->
                _uiState.update {
                    ViewState.Data(data)
                }
            }
            .flowOn(Dispatchers.Default)
            .launchIn(viewModelScope)
    }

    private suspend fun search(): SearchableChooserUiState {
        val data = getCurrentUiStateData() ?: return SearchableChooserUiState.Loading
        delay(1000L)
        val result = launcher.behavior.onSearchQueryChange(cachedSearchText)
        return data.copy(
            chooserItems = result,
            searchQuery = cachedSearchText,
        )
    }

    private fun getCurrentUiStateData(): SearchableChooserUiState.Data? {
        return (uiState.value as? ViewState.Data<SearchableChooserUiState>)?.data as? SearchableChooserUiState.Data
    }
}

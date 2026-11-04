package com.example.dailypulse.sources.presentation

import com.example.dailypulse.BaseViewModel
import com.example.dailypulse.sources.domain.repository.SourcesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val GENERIC_TIMEOUT: Long = 5_000

class SourcesViewModel(
    private val sourcesRepository: SourcesRepository
) : BaseViewModel() {

    private val _state: MutableStateFlow<SourcesUiState> =
        MutableStateFlow(SourcesUiState(isLoading = true))

    val state: StateFlow<SourcesUiState> = _state
        .onStart { getSources() }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(GENERIC_TIMEOUT),
            initialValue = SourcesUiState(isLoading = true)
        )

    fun getSources() {
        scope.launch {
            _state.update {
                SourcesUiState(isLoading = true, sources = state.value.sources)
            }

            val sources = sourcesRepository.getSources()
            _state.update {
                SourcesUiState(sources = sources, isLoading = false)
            }
        }
    }
}
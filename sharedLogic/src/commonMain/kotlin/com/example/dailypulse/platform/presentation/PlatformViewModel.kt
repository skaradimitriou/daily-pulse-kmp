package com.example.dailypulse.platform.presentation

import com.example.dailypulse.BaseViewModel
import com.example.dailypulse.platform.domain.repository.PlatformRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val GENERIC_TIMEOUT = 5000L

class PlatformViewModel(
    private val repository: PlatformRepository
) : BaseViewModel() {

    private val _state: MutableStateFlow<PlatformUiState> =
        MutableStateFlow(PlatformUiState(listOf()))

    val state: StateFlow<PlatformUiState> = _state
        .onStart { getPlatformDetails() }
        .stateIn(
            scope = scope,
            started = SharingStarted.WhileSubscribed(GENERIC_TIMEOUT),
            initialValue = PlatformUiState(listOf())
        )

    fun getPlatformDetails() {
        scope.launch {
            val platformInfo = repository.getPlatformDetails()
            _state.update {
                PlatformUiState(info = platformInfo)
            }
        }
    }
}
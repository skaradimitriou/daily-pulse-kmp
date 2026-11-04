package com.example.dailypulse.sources.presentation

import com.example.dailypulse.sources.domain.model.Source

data class SourcesUiState(
    val sources: List<Source> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null

) {
    val hasError: Boolean
        get() = error != null
}
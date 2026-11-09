package com.example.dailypulse.screens.sources

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.dailypulse.sources.domain.model.Source
import com.example.dailypulse.sources.presentation.SourcesUiState

internal class SourcesPreviewProvider : PreviewParameterProvider<SourcesUiState> {

    override val values: Sequence<SourcesUiState> = sequenceOf(
        SourcesUiState(isLoading = true),
        SourcesUiState(error = "Something went wrong"),
        SourcesUiState(
            sources = listOf(
                Source(
                    id = "abc-news",
                    title = "ABC News",
                    subtitle = "Your trusted source for breaking news and analysis.",
                    url = "https://abcnews.go.com",
                    category = "general",
                    language = "en",
                    country = "us"
                ),
                Source(
                    id = "bbc-news",
                    title = "BBC News",
                    subtitle = "Breaking news, world news, and in-depth analysis.",
                    url = "https://www.bbc.com/news",
                    category = "general",
                    language = "en",
                    country = "gb"
                ),
                Source(
                    id = "cnn",
                    title = "CNN",
                    subtitle = "Latest news, politics, business, and world events.",
                    url = "https://www.cnn.com",
                    category = "general",
                    language = "en",
                    country = "us"
                )
            )
        )
    )
}
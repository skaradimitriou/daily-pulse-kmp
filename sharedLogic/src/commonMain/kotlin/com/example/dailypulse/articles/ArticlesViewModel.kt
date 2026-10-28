package com.example.dailypulse.articles

import com.example.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class ArticlesViewModel : BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(isLoading = true))

    val articlesState: StateFlow<ArticlesState> = _articlesState.asStateFlow()

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            delay(1000L.milliseconds)
            _articlesState.update {
                ArticlesState(articles = mockArticles, isLoading = false)
            }
        }
    }

    private val mockArticles = listOf(
        Article(
            title = "Lorem ipsum",
            description = "Lorem ipsum sit dolor amet",
            date = "2026-10-28",
            imageUrl = "https://www.google.com"
        ),
        Article(
            title = "Lorem ipsum",
            description = "Lorem ipsum sit dolor amet",
            date = "2026-10-28",
            imageUrl = "https://www.google.com"
        ),
        Article(
            title = "Lorem ipsum",
            description = "Lorem ipsum sit dolor amet",
            date = "2026-10-28",
            imageUrl = "https://www.google.com"
        )
    )
}
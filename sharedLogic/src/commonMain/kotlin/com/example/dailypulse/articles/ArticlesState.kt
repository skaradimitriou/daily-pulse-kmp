package com.example.dailypulse.articles

import com.example.dailypulse.articles.domain.model.Article

data class ArticlesState(
    val articles: List<Article> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)
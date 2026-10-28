package com.example.dailypulse.articles

data class ArticlesState(
    val articles: List<Article> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)
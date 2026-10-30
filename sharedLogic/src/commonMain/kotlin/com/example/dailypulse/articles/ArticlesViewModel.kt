package com.example.dailypulse.articles

import com.example.dailypulse.BaseViewModel
import com.example.dailypulse.articles.domain.usecase.ArticlesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val articlesUseCase: ArticlesUseCase
) : BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(isLoading = true))

    val articlesState: StateFlow<ArticlesState> = _articlesState.asStateFlow()

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            val articles = articlesUseCase.invoke()
            _articlesState.update {
                ArticlesState(articles = articles, isLoading = false)
            }
        }
    }
}
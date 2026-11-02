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

    fun getArticles(forceFetch: Boolean = false) {
        scope.launch {
            _articlesState.update {
                ArticlesState(isLoading = true, articles = articlesState.value.articles)
            }

            val articles = articlesUseCase.invoke(forceFetch)
            _articlesState.update {
                ArticlesState(articles = articles, isLoading = false)
            }
        }
    }
}
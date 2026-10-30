package com.example.dailypulse.articles

import com.example.dailypulse.BaseViewModel
import com.example.dailypulse.articles.data.remote.ArticlesService
import com.example.dailypulse.articles.domain.usecase.ArticlesUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ArticlesViewModel : BaseViewModel() {

    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(isLoading = true))

    val articlesState: StateFlow<ArticlesState> = _articlesState.asStateFlow()


    private lateinit var articlesUseCase: ArticlesUseCase

    init {
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }

        val service = ArticlesService(httpClient)

        articlesUseCase = ArticlesUseCase(service = service)
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
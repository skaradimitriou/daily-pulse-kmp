package com.example.dailypulse.articles.di

import com.example.dailypulse.articles.ArticlesViewModel
import com.example.dailypulse.articles.data.remote.ArticlesService
import com.example.dailypulse.articles.domain.usecase.ArticlesUseCase
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val articlesModule = module {

    single<HttpClient> {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    single<ArticlesService> {
        ArticlesService(httpClient = get())
    }

    single<ArticlesUseCase> {
        ArticlesUseCase(service = get())
    }

    factory<ArticlesViewModel> {
        ArticlesViewModel(articlesUseCase = get())
    }
}
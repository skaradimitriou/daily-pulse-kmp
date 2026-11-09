package com.example.dailypulse.articles.di

import com.example.dailypulse.articles.data.ArticlesRepositoryImpl
import com.example.dailypulse.articles.data.local.ArticlesDataSource
import com.example.dailypulse.articles.data.remote.ArticlesService
import com.example.dailypulse.articles.data.remote.ArticlesServiceImpl
import com.example.dailypulse.articles.domain.repository.ArticlesRepository
import com.example.dailypulse.articles.presentation.ArticlesViewModel
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
        ArticlesServiceImpl(httpClient = get())
    }

    single<ArticlesDataSource> {
        ArticlesDataSource(database = get())
    }

    single<ArticlesRepository> {
        ArticlesRepositoryImpl(
            dataSource = get(),
            articlesService = get()
        )
    }

    factory<ArticlesViewModel> {
        ArticlesViewModel(articlesRepository = get())
    }
}
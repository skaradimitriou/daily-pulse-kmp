package com.example.dailypulse.di

import com.example.dailypulse.articles.di.articlesModule
import com.example.dailypulse.articles.presentation.ArticlesViewModel
import com.example.dailypulse.sources.di.sourcesModule
import com.example.dailypulse.sources.presentation.SourcesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {

    val modules = articlesModule + databaseModule + sourcesModule

    startKoin {
        modules(modules)
    }
}

class ArticlesInjector : KoinComponent {

    val articlesViewModel: ArticlesViewModel by inject()
}

class SourcesInjector : KoinComponent {

    val viewModel: SourcesViewModel by inject()
}
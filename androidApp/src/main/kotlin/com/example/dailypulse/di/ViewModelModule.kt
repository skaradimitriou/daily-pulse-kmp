package com.example.dailypulse.di

import com.example.dailypulse.articles.ArticlesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {

    viewModel<ArticlesViewModel> {
        ArticlesViewModel(articlesUseCase = get())
    }
}
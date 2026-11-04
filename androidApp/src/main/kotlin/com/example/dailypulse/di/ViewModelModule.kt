package com.example.dailypulse.di

import com.example.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {

    viewModel<ArticlesViewModel> {
        ArticlesViewModel(articlesRepository = get())
    }
}
package com.example.dailypulse.di

import com.example.dailypulse.articles.presentation.ArticlesViewModel
import com.example.dailypulse.platform.presentation.PlatformViewModel
import com.example.dailypulse.sources.presentation.SourcesViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {

    viewModel<ArticlesViewModel> {
        ArticlesViewModel(articlesRepository = get())
    }

    viewModel<SourcesViewModel> {
        SourcesViewModel(sourcesRepository = get())
    }

    viewModel<PlatformViewModel> {
        PlatformViewModel(repository = get())
    }
}
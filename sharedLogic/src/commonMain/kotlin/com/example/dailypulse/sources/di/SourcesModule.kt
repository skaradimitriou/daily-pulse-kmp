package com.example.dailypulse.sources.di

import com.example.dailypulse.sources.data.SourcesRepositoryImpl
import com.example.dailypulse.sources.data.remote.SourcesService
import com.example.dailypulse.sources.domain.repository.SourcesRepository
import com.example.dailypulse.sources.presentation.SourcesViewModel
import org.koin.dsl.module

val sourcesModule = module {

    single<SourcesService> {
        SourcesService(httpClient = get())
    }

    single<SourcesRepository> {
        SourcesRepositoryImpl(remoteDataSource = get())
    }

    factory<SourcesViewModel> {
        SourcesViewModel(sourcesRepository = get())
    }
}
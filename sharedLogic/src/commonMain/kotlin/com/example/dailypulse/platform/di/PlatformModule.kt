package com.example.dailypulse.platform.di

import com.example.dailypulse.platform.data.local.PlatformRepositoryImpl
import com.example.dailypulse.platform.domain.repository.PlatformRepository
import com.example.dailypulse.platform.presentation.PlatformViewModel
import org.koin.dsl.module

val platformModule = module {

    single<PlatformRepository> {
        PlatformRepositoryImpl()
    }

    single<PlatformViewModel> {
        PlatformViewModel(repository = get())
    }
}
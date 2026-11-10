package com.example.dailypulse.platform.data.local

import com.example.dailypulse.platform.domain.model.PlatformInfoItem
import com.example.dailypulse.platform.domain.model.getPlatformDetailsList
import com.example.dailypulse.platform.domain.repository.PlatformRepository

internal class PlatformRepositoryImpl : PlatformRepository {

    override suspend fun getPlatformDetails(): List<PlatformInfoItem> = getPlatformDetailsList()
}
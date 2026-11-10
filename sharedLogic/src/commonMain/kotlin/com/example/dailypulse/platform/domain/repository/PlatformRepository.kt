package com.example.dailypulse.platform.domain.repository

import com.example.dailypulse.platform.domain.model.PlatformInfoItem

interface PlatformRepository {

    suspend fun getPlatformDetails(): List<PlatformInfoItem>
}
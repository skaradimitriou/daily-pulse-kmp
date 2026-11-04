package com.example.dailypulse.sources.domain.repository

import com.example.dailypulse.sources.domain.model.Source

interface SourcesRepository {

    suspend fun getSources(): List<Source>
}
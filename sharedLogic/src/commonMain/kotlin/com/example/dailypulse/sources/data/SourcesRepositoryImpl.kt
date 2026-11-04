package com.example.dailypulse.sources.data

import com.example.dailypulse.sources.data.remote.SourcesService
import com.example.dailypulse.sources.data.remote.mapper.toSource
import com.example.dailypulse.sources.domain.model.Source
import com.example.dailypulse.sources.domain.repository.SourcesRepository

internal class SourcesRepositoryImpl(
    private val remoteDataSource: SourcesService
) : SourcesRepository {

    override suspend fun getSources(): List<Source> = remoteDataSource.fetchSources()
        .map { sourceRaw -> sourceRaw.toSource() }
}
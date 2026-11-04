package com.example.dailypulse.sources.data.remote

import com.example.dailypulse.sources.data.remote.model.SourceRaw
import com.example.dailypulse.sources.data.remote.model.SourceResponseRaw
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

internal class SourcesService(private val httpClient: HttpClient) {

    private val apiKey = "f67ace1b27b24ce4b95c7f71fde88920"

    suspend fun fetchSources(): List<SourceRaw> {
        val response: SourceResponseRaw = httpClient.get(
            "https://newsapi.org/v2/top-headlines/sources?apiKey=$apiKey"
        ).body()

        return response.sources
    }
}
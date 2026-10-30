package com.example.dailypulse.articles.data.remote

import com.example.dailypulse.articles.data.remote.model.ArticleRaw
import com.example.dailypulse.articles.data.remote.model.ArticlesResponseRaw
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(val httpClient: HttpClient) {

    private val country = "us"
    private val category = "business"

    private val apiKey = "apiKey"

    suspend fun fetchArticles(): List<ArticleRaw> {
        val response: ArticlesResponseRaw = httpClient.get(
            "https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey"
        ).body()

        return response.articles
    }
}
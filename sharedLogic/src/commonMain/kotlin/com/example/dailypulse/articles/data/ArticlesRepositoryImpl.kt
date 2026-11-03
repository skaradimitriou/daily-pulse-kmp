package com.example.dailypulse.articles.data

import com.example.dailypulse.articles.data.local.ArticlesDataSource
import com.example.dailypulse.articles.data.remote.ArticlesService
import com.example.dailypulse.articles.data.remote.mapper.toArticles
import com.example.dailypulse.articles.domain.repository.ArticlesRepository
import com.example.dailypulse.articles.domain.model.Article

internal class ArticlesRepositoryImpl(
    private val dataSource: ArticlesDataSource,
    private val articlesService: ArticlesService
) : ArticlesRepository {

    override suspend fun getArticles(forceFetch: Boolean): List<Article> {
        if (forceFetch) {
            dataSource.clearArticles()
            return fetchArticles()
        }

        val articlesFromDb = dataSource.getAllArticles()

        if (articlesFromDb.isEmpty()) {
            return fetchArticles()
        }

        return articlesFromDb
    }

    private suspend fun fetchArticles(): List<Article> {
        val fetchedArticles = articlesService.fetchArticles().toArticles()
        dataSource.insertArticles(fetchedArticles)
        return fetchedArticles
    }
}
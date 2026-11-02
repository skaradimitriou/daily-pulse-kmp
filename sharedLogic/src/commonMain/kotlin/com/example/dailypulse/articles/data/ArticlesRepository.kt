package com.example.dailypulse.articles.data

import com.example.dailypulse.articles.data.local.ArticlesDataSource
import com.example.dailypulse.articles.data.remote.ArticlesService
import com.example.dailypulse.articles.data.remote.model.ArticleRaw

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val articlesService: ArticlesService
) {

    suspend fun getArticles(forceFetch: Boolean): List<ArticleRaw> {
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

    private suspend fun fetchArticles(): List<ArticleRaw> {
        val fetchedArticles = articlesService.fetchArticles()
        dataSource.insertArticles(fetchedArticles)
        return fetchedArticles
    }
}
package com.example.dailypulse.articles.data

import com.example.dailypulse.articles.data.local.ArticlesDataSource
import com.example.dailypulse.articles.data.remote.ArticlesService
import com.example.dailypulse.articles.data.remote.model.ArticleRaw

class ArticlesRepository(
    private val dataSource: ArticlesDataSource,
    private val articlesService: ArticlesService
) {

    suspend fun getArticles(): List<ArticleRaw> {
        val articlesFromDb = dataSource.getAllArticles()

        if (articlesFromDb.isEmpty()) {
            val fetchedArticles = articlesService.fetchArticles()
            dataSource.insertArticles(fetchedArticles)
            return fetchedArticles
        }

        return articlesFromDb
    }
}
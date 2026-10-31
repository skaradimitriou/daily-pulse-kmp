package com.example.dailypulse.articles.data.local

import com.example.dailypulse.articles.data.remote.model.ArticleRaw
import example.dailypulse.db.DailyPulseDatabase

class ArticlesDataSource(
    private val database: DailyPulseDatabase
) {

    fun getAllArticles(): List<ArticleRaw> = database.dailyPulseDatabaseQueries
        .selectAllArticles(::mapToArticleRaw).executeAsList()

    fun insertArticles(articlesRaw: List<ArticleRaw>) {
        database.dailyPulseDatabaseQueries.transaction {
            articlesRaw.forEach { articlesRaw ->
                insertArticle(articlesRaw)
            }
        }
    }

    fun clearArticles() = database.dailyPulseDatabaseQueries.removeFromArticles()

    private fun mapToArticleRaw(
        title: String,
        description: String?,
        date: String,
        imageUrl: String?,
    ) = ArticleRaw(title, description, imageUrl, date)

    private fun insertArticle(articleRaw: ArticleRaw) {
        database.dailyPulseDatabaseQueries.insertArticle(
            title = articleRaw.title,
            description = articleRaw.description,
            imageUrl = articleRaw.imageUrl,
            date = articleRaw.date
        )
    }
}
package com.example.dailypulse.articles.data.local

import com.example.dailypulse.articles.domain.model.Article
import example.dailypulse.db.DailyPulseDatabase

class ArticlesDataSource(
    private val database: DailyPulseDatabase
) {

    fun getAllArticles(): List<Article> = database.dailyPulseDatabaseQueries
        .selectAllArticles(::mapToArticle).executeAsList()

    fun insertArticles(articlesRaw: List<Article>) {
        database.dailyPulseDatabaseQueries.transaction {
            articlesRaw.forEach { articlesRaw ->
                insertArticle(articlesRaw)
            }
        }
    }

    fun clearArticles() = database.dailyPulseDatabaseQueries.removeFromArticles()

    private fun mapToArticle(
        title: String,
        description: String?,
        date: String,
        imageUrl: String?,
    ) = Article(title, description.orEmpty(), date, imageUrl.orEmpty())

    private fun insertArticle(article: Article) {
        database.dailyPulseDatabaseQueries.insertArticle(
            title = article.title,
            description = article.description,
            imageUrl = article.imageUrl,
            date = article.date
        )
    }
}
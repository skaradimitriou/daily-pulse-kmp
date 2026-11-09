package com.example.dailypulse.provider

import com.example.dailypulse.articles.data.remote.model.ArticleRaw
import com.example.dailypulse.articles.domain.model.Article

internal fun getArticlesFromRemote(): List<ArticleRaw> = listOf(
    ArticleRaw(
        title = "Using miles to fly for Thanksgiving? These are the most expensive and cheapest routes - CNBC",
        description = "Thanksgiving airfare will cost more than last year as fares rise and strong demand continues.",
        imageUrl = "https://image.cnbcfm.com/api/v1/image/108330347-1783030838668-gettyimages-2283797997-JULY_4_TRAVEL.jpeg?v=1783031103&w=1920&h=1080",
        date = "2026-11-08T13:00:01Z"
    ),
    ArticleRaw(
        title = "I lost my tech job and couldn't afford my mortgage. Now I live in a 100-square-foot shed in the yard. - Business Insider",
        description = "After a tech layoff, Lauren Patton couldn't afford her mortgage. So she built a tiny home and lives there while renting the main home on Airbnb.",
        imageUrl = "https://g.foolcdn.com/image/?url=https%3A%2F%2Fcdn.content.foolcdn.com%2Fimages%2F1umn9qeh%2Fproduction%2F54ea6c3211832f4cdaf7959240d39fb83acfc927-1200x780.png",
        date = "2026-11-08T11:36:00Z"
    )
)

internal fun getArticleRaw(
    description: String?,
    imageUrl: String?,
): ArticleRaw = ArticleRaw(
    title = "Using miles to fly for Thanksgiving? These are the most expensive and cheapest routes - CNBC",
    description = description,
    imageUrl = imageUrl,
    date = "2026-11-08T13:00:01Z"
)

internal fun getArticles(): List<Article> = listOf(
    Article(
        title = "Using miles to fly for Thanksgiving? These are the most expensive and cheapest routes - CNBC",
        description = "Thanksgiving airfare will cost more than last year as fares rise and strong demand continues.",
        imageUrl = "https://image.cnbcfm.com/api/v1/image/108330347-1783030838668-gettyimages-2283797997-JULY_4_TRAVEL.jpeg?v=1783031103&w=1920&h=1080",
        date = "2026-11-08T13:00:01Z"
    ),
    Article(
        title = "I lost my tech job and couldn't afford my mortgage. Now I live in a 100-square-foot shed in the yard. - Business Insider",
        description = "After a tech layoff, Lauren Patton couldn't afford her mortgage. So she built a tiny home and lives there while renting the main home on Airbnb.",
        imageUrl = "https://g.foolcdn.com/image/?url=https%3A%2F%2Fcdn.content.foolcdn.com%2Fimages%2F1umn9qeh%2Fproduction%2F54ea6c3211832f4cdaf7959240d39fb83acfc927-1200x780.png",
        date = "2026-11-08T11:36:00Z"
    )
)

internal fun getArticle(
    description: String,
    imageUrl: String,
): Article = Article(
    title = "Using miles to fly for Thanksgiving? These are the most expensive and cheapest routes - CNBC",
    description = description,
    imageUrl = imageUrl,
    date = "2026-11-08T13:00:01Z"
)
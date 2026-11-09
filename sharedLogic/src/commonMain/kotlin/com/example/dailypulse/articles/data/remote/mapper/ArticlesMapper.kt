package com.example.dailypulse.articles.data.remote.mapper

import com.example.dailypulse.articles.data.remote.model.ArticleRaw
import com.example.dailypulse.articles.domain.model.Article

internal fun List<ArticleRaw>.toArticles(): List<Article> = mapNotNull { raw -> raw.toArticle() }

internal fun ArticleRaw.toArticle(): Article = Article(
    title = title,
    description = description.orEmpty(),
    imageUrl = imageUrl.orEmpty(),
    date = date
)
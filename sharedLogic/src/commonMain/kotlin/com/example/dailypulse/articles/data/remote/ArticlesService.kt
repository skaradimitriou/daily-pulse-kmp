package com.example.dailypulse.articles.data.remote

import com.example.dailypulse.articles.data.remote.model.ArticleRaw

internal interface ArticlesService {
    suspend fun fetchArticles(): List<ArticleRaw>
}
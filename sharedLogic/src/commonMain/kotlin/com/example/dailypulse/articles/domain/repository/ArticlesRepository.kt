package com.example.dailypulse.articles.domain.repository

import com.example.dailypulse.articles.domain.model.Article

interface ArticlesRepository {

    suspend fun getArticles(forceFetch: Boolean): List<Article>
}
package com.example.dailypulse.articles.domain.usecase

import com.example.dailypulse.articles.data.remote.ArticlesService
import com.example.dailypulse.articles.data.remote.mapper.toArticles
import com.example.dailypulse.articles.domain.model.Article

class ArticlesUseCase(private val service: ArticlesService) {

    suspend fun invoke(): List<Article> = service.fetchArticles().toArticles()
}
package com.example.dailypulse.articles.domain.usecase

import com.example.dailypulse.articles.data.ArticlesRepository
import com.example.dailypulse.articles.data.remote.mapper.toArticles
import com.example.dailypulse.articles.domain.model.Article

class ArticlesUseCase(private val articlesRepository: ArticlesRepository) {

    suspend fun invoke(): List<Article> = articlesRepository.getArticles().toArticles()
}
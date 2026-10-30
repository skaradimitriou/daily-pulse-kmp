package com.example.dailypulse.articles.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleRaw(
    val title: String,
    val description: String? = null,
    @SerialName("urlToImage")
    val imageUrl: String? = null,
    @SerialName("publishedAt")
    val date: String
)
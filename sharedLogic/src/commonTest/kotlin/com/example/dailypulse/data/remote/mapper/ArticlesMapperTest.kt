package com.example.dailypulse.data.remote.mapper

import com.example.dailypulse.articles.data.remote.mapper.toArticle
import com.example.dailypulse.articles.data.remote.mapper.toArticles
import com.example.dailypulse.provider.getArticle
import com.example.dailypulse.provider.getArticleRaw
import com.example.dailypulse.provider.getArticles
import com.example.dailypulse.provider.getArticlesFromRemote
import kotlin.test.Test
import kotlin.test.assertEquals

class ArticlesMapperTest {

    @Test
    fun `given list of remote models, when mapping, then return domain model list`() {
        val remoteModels = getArticlesFromRemote()

        val expected = getArticles()

        assertEquals(expected, remoteModels.toArticles())
    }

    @Test
    fun `given raw model, when mapping, then return domain model`() {
        val rawModel = getArticleRaw(
            description = "description",
            imageUrl = "imageUrl"
        )

        val expected = getArticle(
            description = "description",
            imageUrl = "imageUrl"
        )

        assertEquals(expected, rawModel.toArticle())
    }

    @Test
    fun `given raw model with null description & imageUrl, when mapping, then return domain model`() {
        val rawModel = getArticleRaw(description = null, imageUrl = null)

        val expected = getArticle(
            description = "Click to find out more",
            imageUrl = ""
        )

        assertEquals(expected, rawModel.toArticle())
    }
}
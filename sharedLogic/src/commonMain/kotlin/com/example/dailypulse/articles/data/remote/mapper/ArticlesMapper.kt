package com.example.dailypulse.articles.data.remote.mapper

import com.example.dailypulse.articles.data.remote.model.ArticleRaw
import com.example.dailypulse.articles.domain.model.Article
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs
import kotlin.time.Clock
import kotlin.time.Instant

internal fun List<ArticleRaw>.toArticles(): List<Article> = mapNotNull { raw ->
    Article(
        title = raw.title,
        description = raw.description ?: "Click to find out more",
        imageUrl = raw.imageUrl.orEmpty(),
        date = raw.date.asDaysAgoString()
    )
}

private fun String.asDaysAgoString(): String {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val days = today.daysUntil(
        Instant.parse(this).toLocalDateTime(TimeZone.currentSystemDefault()).date
    )

    val result = when {
        abs(days) > 1 -> "${abs(days)} days ago"
        abs(days) == 1 -> "Yesterday"
        else -> "Today"
    }

    return result
}
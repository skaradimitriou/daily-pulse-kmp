package com.example.dailypulse.screens.articles

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Announcement
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.dailypulse.articles.domain.model.Article
import com.example.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArticlesScreen(
    onSourcesClick: () -> Unit,
    articlesViewModel: ArticlesViewModel = koinViewModel()
) {
    val articlesState by articlesViewModel.articlesState.collectAsStateWithLifecycle()

    Column {
        AppBar(onSourcesClick)
        when {
            articlesState.hasError -> {
                ErrorMessage(articlesState.error.orEmpty())
            }

            else -> {
                ArticlesList(
                    articles = articlesState.articles,
                    isRefreshing = articlesState.isLoading,
                    onRefresh = {
                        articlesViewModel.getArticles(true)
                    }
                )
            }
        }
    }
}

@Composable
private fun AppBar(onSourcesClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Articles") },
        actions = {
            IconButton(onClick = onSourcesClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Announcement,
                    contentDescription = "Announcement"
                )
            }
        }
    )
}

@Composable
fun ArticlesList(articles: List<Article>, isRefreshing: Boolean, onRefresh: () -> Unit) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(articles) {
                ArticleItem(it)
            }
        }
    }
}

@Composable
private fun ArticleItem(article: Article) {
    Column(modifier = Modifier.fillMaxWidth()) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.description)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
private fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}
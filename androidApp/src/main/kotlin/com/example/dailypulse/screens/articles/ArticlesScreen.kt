package com.example.dailypulse.screens.articles

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.dailypulse.R
import com.example.dailypulse.articles.domain.model.Article
import com.example.dailypulse.articles.presentation.ArticlesState
import com.example.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun ArticlesScreen(
    onSourcesClick: () -> Unit,
    onPlatformClick: () -> Unit,
    articlesViewModel: ArticlesViewModel = koinViewModel()
) {
    val state by articlesViewModel.articlesState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { AppBar(onSourcesClick, onPlatformClick) },
        content = { innerPadding ->
            ArticlesContent(
                state = state,
                innerPadding = innerPadding,
                onRefresh = { articlesViewModel.getArticles(true) }
            )
        }
    )
}

@Composable
private fun ArticlesContent(
    state: ArticlesState,
    onRefresh: () -> Unit,
    innerPadding: PaddingValues = PaddingValues()
) {
    when {
        state.hasError -> {
            ErrorMessage(
                modifier = Modifier.padding(innerPadding),
                message = state.error.orEmpty()
            )
        }

        else -> {
            ArticlesList(
                modifier = Modifier.padding(innerPadding),
                articles = state.articles,
                isRefreshing = state.isLoading,
                onRefresh = onRefresh
            )
        }
    }
}

@Composable
private fun AppBar(
    onSourcesClick: () -> Unit,
    onPlatformClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.articles_screen_title)) },
        actions = {
            IconButton(
                onClick = onSourcesClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = stringResource(R.string.sources_screen_title)
                )
            }

            IconButton(
                onClick = onPlatformClick
            ) {
                Icon(
                    imageVector = Icons.Filled.Devices,
                    contentDescription = stringResource(R.string.platform_screen_title)
                )
            }
        }
    )
}

@Composable
private fun ArticlesList(
    articles: List<Article>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
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
            contentDescription = article.title
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            with(article) {
                Text(
                    text = title,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
                )

                val description = description.ifEmpty {
                    stringResource(R.string.empty_article_description)
                }
                Text(text = description)

                Text(
                    text = displayDate,
                    style = TextStyle(color = Color.Gray),
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

@Composable
private fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ArticlesScreenPreview(
    @PreviewParameter(ArticlesPreviewProvider::class) state: ArticlesState
) {
    ArticlesContent(
        state = state,
        onRefresh = {}
    )
}
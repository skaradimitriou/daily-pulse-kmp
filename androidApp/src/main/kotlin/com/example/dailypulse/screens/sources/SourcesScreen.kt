package com.example.dailypulse.screens.sources

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.example.dailypulse.R
import com.example.dailypulse.sources.domain.model.Source
import com.example.dailypulse.sources.presentation.SourcesUiState
import com.example.dailypulse.sources.presentation.SourcesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun SourcesScreen(
    onBackClick: () -> Unit,
    viewModel: SourcesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { AppBar(onBackClick) }
    ) { innerPadding ->
        SourcesContent(state = state, innerPadding = innerPadding)
    }
}

@Composable
internal fun SourcesContent(
    state: SourcesUiState,
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
            SourcesList(
                modifier = Modifier.padding(innerPadding),
                sources = state.sources
            )
        }
    }
}

@Composable
private fun AppBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.sources_screen_title)) },
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    )
}

@Composable
private fun SourcesList(sources: List<Source>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = sources, key = { it.id }) {
            SourceItem(it)
        }
    }
}

@Composable
private fun SourceItem(source: Source) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = source.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Text(text = source.subtitle)
        Text(
            text = source.languageWithCountry,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
private fun ErrorMessage(message: String, modifier: Modifier = Modifier) {
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
private fun SourcesScreenPreview(
    @PreviewParameter(SourcesPreviewProvider::class) state: SourcesUiState
) {
    SourcesContent(state = state)
}
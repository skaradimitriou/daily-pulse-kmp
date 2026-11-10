package com.example.dailypulse.screens.platform

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dailypulse.R
import com.example.dailypulse.platform.domain.model.InfoType
import com.example.dailypulse.platform.domain.model.PlatformInfoItem
import com.example.dailypulse.platform.presentation.PlatformViewModel
import com.example.dailypulse.screens.platform.mapper.getStringResourceId
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun PlatformScreen(
    onBackClick: () -> Unit,
    viewModel: PlatformViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    PlatformContent(items = state.info, onBackClick = onBackClick)
}

@Composable
private fun PlatformContent(
    items: List<PlatformInfoItem>,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { AppBar(onBackClick) }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(all = 16.dp)
        ) {
            items(items, key = { it.type }) { item ->
                val title = stringResource(id = item.getStringResourceId())
                PlatformDetail(title, item.value)
            }
        }
    }
}

@Composable
private fun PlatformDetail(title: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
        )
        Text(text = value)
    }
}

@Composable
private fun AppBar(onBackClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.platform_screen_title)) },
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

@Preview(showBackground = true)
@Composable
private fun PlatformScreenPreview() {
    val items = listOf(
        PlatformInfoItem(
            type = InfoType.OS_NAME,
            value = "Android"
        ),
        PlatformInfoItem(
            type = InfoType.OS_VERSION,
            value = "API level 34"
        ),
        PlatformInfoItem(
            type = InfoType.DEVICE_MODEL,
            value = "Xiaomi Device"
        ),
        PlatformInfoItem(
            type = InfoType.SCREEN_DENSITY,
            value = "4"
        )
    )

    PlatformContent(items = items, onBackClick = {})
}

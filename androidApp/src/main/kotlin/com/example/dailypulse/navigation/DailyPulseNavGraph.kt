package com.example.dailypulse.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.dailypulse.screens.articles.ArticlesScreen
import com.example.dailypulse.screens.platform.PlatformScreen
import com.example.dailypulse.screens.sources.SourcesScreen

@Composable
internal fun DailyPulseNavGraph() {
    val backStack = remember { mutableStateListOf<Screen>(Screen.Articles) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Screen.Articles -> NavEntry(key) {
                    ArticlesScreen(
                        onSourcesClick = {
                            backStack.add(Screen.Sources)
                        },
                        onPlatformClick = {
                            backStack.add(Screen.Platform)
                        }
                    )
                }

                is Screen.Sources -> NavEntry(key) {
                    SourcesScreen(
                        onBackClick = {
                            backStack.removeLastOrNull()
                        }
                    )
                }

                is Screen.Platform -> NavEntry(key) {
                    PlatformScreen(
                        onBackClick = {
                            backStack.removeLastOrNull()
                        }
                    )
                }
            }
        }
    )
}
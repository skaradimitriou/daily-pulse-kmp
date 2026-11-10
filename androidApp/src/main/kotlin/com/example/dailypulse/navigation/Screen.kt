package com.example.dailypulse.navigation

import androidx.navigation3.runtime.NavKey

internal sealed interface Screen : NavKey {

    data object Articles : Screen
    data object Sources : Screen

    data object Platform : Screen
}

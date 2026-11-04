package com.example.dailypulse.sources.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
internal data class SourceRaw(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
)

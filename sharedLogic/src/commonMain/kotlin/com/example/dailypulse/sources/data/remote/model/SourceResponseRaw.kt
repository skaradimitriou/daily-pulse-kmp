package com.example.dailypulse.sources.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
internal data class SourceResponseRaw(
    val status: String,
    val sources: List<SourceRaw>
)

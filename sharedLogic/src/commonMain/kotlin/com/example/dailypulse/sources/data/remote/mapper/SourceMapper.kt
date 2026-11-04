package com.example.dailypulse.sources.data.remote.mapper

import com.example.dailypulse.sources.data.remote.model.SourceRaw
import com.example.dailypulse.sources.domain.model.Source

internal fun SourceRaw.toSource(): Source = Source(
    id = id,
    title = name,
    subtitle = description,
    url = url,
    category = category,
    language = language,
    country = country
)
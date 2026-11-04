package com.example.dailypulse.sources.domain.model

data class Source(
    val id: String,
    val title: String,
    val subtitle: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
) {
    val languageWithCountry: String = "$language - $country"
}

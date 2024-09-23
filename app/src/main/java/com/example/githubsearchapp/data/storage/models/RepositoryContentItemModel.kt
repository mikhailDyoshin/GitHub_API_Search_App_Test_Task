package com.example.githubsearchapp.data.storage.models

import com.squareup.moshi.Json

data class RepositoryContentItemModel(
    val type: String?,
    val name: String?,
    val path: String?,
    @Json(name = "html_url")
    val htmlURL: String?
)

package com.example.githubsearchapp.domain.models

import com.squareup.moshi.Json

data class User(
    val login: String?,
    val avatarUrl: String?,
    val score: Float?
)

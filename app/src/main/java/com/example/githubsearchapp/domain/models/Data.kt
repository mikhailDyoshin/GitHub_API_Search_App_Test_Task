package com.example.githubsearchapp.domain.models

sealed class Data {
    data class Repository(
        val name: String?,
        val description: String?,
        val numberOfForks: Int?
    )

    data class User(
        val login: String?,
        val avatarUrl: String?,
        val score: Float?
    )
}
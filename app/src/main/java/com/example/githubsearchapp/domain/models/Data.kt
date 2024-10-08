package com.example.githubsearchapp.domain.models

sealed class Data {
    data class Repository(
        val name: String?,
        val description: String?,
        val numberOfForks: Int?,
        val owner: User?
    )

    data class User(
        val login: String?,
        val avatarURL: String?,
        val score: Float?,
        val htmlURL: String?
    )
}
package com.example.githubsearchapp.presentation.searchScreen.state

sealed class SearchListItemState {
    data class UserState(
        val name: String,
        val avatarURL: String,
        val score: Float,
    ) : SearchListItemState()

    data class RepositoryState(val name: String, val description: String) : SearchListItemState()

}
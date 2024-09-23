package com.example.githubsearchapp.presentation.repositoryScreen.state

import com.example.githubsearchapp.common.RepositoryContentItemType

data class RepositoryContentItemState(
    val name: String?,
    val type: RepositoryContentItemType?,
    val path: String?,
    val htmlURL: String?
)

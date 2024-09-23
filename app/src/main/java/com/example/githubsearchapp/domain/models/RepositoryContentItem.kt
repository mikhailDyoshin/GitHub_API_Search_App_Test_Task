package com.example.githubsearchapp.domain.models

import com.example.githubsearchapp.common.RepositoryContentItemType

data class RepositoryContentItem(
    val name: String?,
    val type: RepositoryContentItemType?,
    val path: String?,
    val htmlURL: String?
)

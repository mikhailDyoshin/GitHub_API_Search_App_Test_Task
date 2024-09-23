package com.example.githubsearchapp.domain

import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.domain.models.RepositoryContentItem
import com.example.githubsearchapp.domain.models.RepositoryContentRequestData
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun getData(searchInput: String): Flow<Resource<List<Any>>>

    fun getRepositoryContents(requestData: RepositoryContentRequestData): Flow<Resource<List<RepositoryContentItem>>>
}
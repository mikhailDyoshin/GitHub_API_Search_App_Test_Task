package com.example.githubsearchapp.domain

import com.example.githubsearchapp.common.Resource
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun getData(searchInput: String): Flow<Resource<List<Any>>>
}
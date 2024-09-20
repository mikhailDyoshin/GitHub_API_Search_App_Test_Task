package com.example.githubsearchapp.domain

import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.domain.models.User
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun getUsers(searchInput: String): Flow<Resource<List<User>>>

}
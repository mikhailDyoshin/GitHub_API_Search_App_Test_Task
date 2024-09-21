package com.example.githubsearchapp.domain

import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.domain.models.Data
import com.example.githubsearchapp.domain.models.Repository
import com.example.githubsearchapp.domain.models.User
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    fun getUsers(searchInput: String): Flow<Resource<List<Data.User>>>

    fun getRepositories(repositoryName: String): Flow<Resource<List<Data.Repository>>>

}
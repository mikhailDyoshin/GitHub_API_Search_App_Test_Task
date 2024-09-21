package com.example.githubsearchapp.domain.usecases

import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.domain.SearchRepository
import com.example.githubsearchapp.domain.models.Data
import com.example.githubsearchapp.domain.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: SearchRepository) {

    operator fun invoke(searchInput: String): Flow<Resource<List<Data.User>>> {
        return repository.getUsers(searchInput = searchInput)
    }

}
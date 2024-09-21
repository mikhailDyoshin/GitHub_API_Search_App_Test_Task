package com.example.githubsearchapp.domain.usecases

import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.domain.SearchRepository
import com.example.githubsearchapp.domain.models.Data
import com.example.githubsearchapp.domain.models.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(private val repository: SearchRepository) {

    operator fun invoke(searchInput: String): Flow<Resource<List<Data.Repository>>> {
        return repository.getRepositories(repositoryName = searchInput)
    }

}
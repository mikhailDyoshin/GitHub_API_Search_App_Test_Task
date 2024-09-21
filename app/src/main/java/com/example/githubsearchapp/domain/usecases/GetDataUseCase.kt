package com.example.githubsearchapp.domain.usecases

import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.domain.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDataUseCase @Inject constructor(private val repository: SearchRepository) {
    operator fun invoke(searchInput: String): Flow<Resource<List<Any>>> {
        return repository.getData(searchInput)
    }
}
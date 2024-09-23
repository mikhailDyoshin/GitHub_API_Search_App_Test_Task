package com.example.githubsearchapp.domain.usecases

import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.domain.SearchRepository
import com.example.githubsearchapp.domain.models.RepositoryContentItem
import com.example.githubsearchapp.domain.models.RepositoryContentRequestData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRepositoryContentUseCase @Inject constructor(private val repository: SearchRepository) {

    operator fun invoke(requestData: RepositoryContentRequestData): Flow<Resource<List<RepositoryContentItem>>> {
        return repository.getRepositoryContents(requestData = requestData)
    }

}
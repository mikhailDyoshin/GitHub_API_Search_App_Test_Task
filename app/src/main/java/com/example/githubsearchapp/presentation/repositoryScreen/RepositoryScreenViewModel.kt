package com.example.githubsearchapp.presentation.repositoryScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.domain.models.RepositoryContentRequestData
import com.example.githubsearchapp.domain.usecases.GetRepositoryContentUseCase
import com.example.githubsearchapp.presentation.repositoryScreen.state.RepositoryContentState
import com.example.githubsearchapp.presentation.repositoryScreen.state.RequestDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RepositoryScreenViewModel @Inject constructor(private val getRepositoryContentUseCase: GetRepositoryContentUseCase) :
    ViewModel() {

    private val _repositoryContentState: MutableState<RepositoryContentState> = mutableStateOf(
        RepositoryContentState(
            repositoryContent = emptyList(),
            status = Resource.Status.SUCCESS
        )
    )

    val repositoryContentState: State<RepositoryContentState> = _repositoryContentState

    fun getRepositoryContents(requestData: RequestDataState) {

        getRepositoryContentUseCase(
            requestData = RepositoryContentRequestData(
                owner = requestData.owner,
                path = requestData.path,
                repository = requestData.repository
            )
        ).onEach { resource ->
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    resource.data?.let {
                        _repositoryContentState.value = RepositoryContentState(
                            repositoryContent = it.map { item -> "${item.name} ${item.type}" },
                            status = Resource.Status.SUCCESS
                        )
                    }
                }

                Resource.Status.ERROR -> {
                    _repositoryContentState.value = RepositoryContentState(
                        repositoryContent = emptyList(),
                        status = Resource.Status.ERROR
                    )
                }

                Resource.Status.LOADING -> {
                    _repositoryContentState.value = RepositoryContentState(
                        repositoryContent = emptyList(),
                        status = Resource.Status.LOADING
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}
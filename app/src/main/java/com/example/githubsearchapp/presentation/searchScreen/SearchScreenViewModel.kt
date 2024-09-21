package com.example.githubsearchapp.presentation.searchScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.domain.models.Data
import com.example.githubsearchapp.domain.models.Repository
import com.example.githubsearchapp.domain.models.User
import com.example.githubsearchapp.domain.usecases.GetRepositoriesUseCase
import com.example.githubsearchapp.domain.usecases.GetUsersUseCase
import com.example.githubsearchapp.presentation.searchScreen.state.SearchListItemState
import com.example.githubsearchapp.presentation.searchScreen.state.SearchScreenListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) :
    ViewModel() {

    private val _searchInputState: MutableState<String> = mutableStateOf("")

    val searchInputState: State<String> = _searchInputState

    private val _searchListState: MutableState<SearchScreenListState> =
        mutableStateOf(SearchScreenListState(list = emptyList(), status = Resource.Status.SUCCESS))

    val searchListState: State<SearchScreenListState> = _searchListState

    fun updateSearchInput(searchInput: String) {
        _searchInputState.value = searchInput
    }

    fun searchUsers() {
        viewModelScope.launch {
            val users = getUsersUseCase(searchInput = _searchInputState.value)

            val repositories = getRepositoriesUseCase(searchInput = _searchInputState.value)

            val common = merge(users, repositories).map { resource ->
                resource.data?.let {
                    SearchScreenListState(list = it.map { item ->

                        when (item) {
                            is Data.Repository -> {
                                SearchListItemState.RepositoryState(
                                    name = item.name ?: "No name",
                                    description = item.description ?: "No description",
                                )
                            }

                            is Data.User -> {
                                SearchListItemState.UserState(
                                    name = item.login ?: "No name",
                                    avatarURL = item.avatarUrl ?: "",
                                    score = item.score ?: 0f
                                )
                            }

                            else -> {
                                SearchListItemState.RepositoryState(
                                    name = "",
                                    description = "",
                                )
                            }
                        }


                    }, status = resource.status)
                }
            }

            common.collect {
                if (it != null) {
                    _searchListState.value = it
                }
            }
        }
    }
}
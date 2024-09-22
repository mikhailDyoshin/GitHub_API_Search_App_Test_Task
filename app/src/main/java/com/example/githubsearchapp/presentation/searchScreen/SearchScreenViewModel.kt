package com.example.githubsearchapp.presentation.searchScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.domain.models.Data
import com.example.githubsearchapp.domain.usecases.GetDataUseCase
import com.example.githubsearchapp.presentation.searchScreen.state.SearchListItemState
import com.example.githubsearchapp.presentation.searchScreen.state.SearchScreenListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase
) : ViewModel() {

    private val _searchInputState: MutableState<String> = mutableStateOf("")

    val searchInputState: State<String> = _searchInputState

    private val _searchListState: MutableState<SearchScreenListState> =
        mutableStateOf(SearchScreenListState(list = emptyList(), status = Resource.Status.SUCCESS))

    val searchListState: State<SearchScreenListState> = _searchListState

    fun updateSearchInput(searchInput: String) {
        _searchInputState.value = searchInput
    }

    fun searchUsers() {
        getDataUseCase(searchInput = _searchInputState.value).onEach { resource ->

            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    resource.data?.let {
                        _searchListState.value = SearchScreenListState(list = it.map { item ->

                            when (item) {
                                is Data.Repository -> {
                                    SearchListItemState.RepositoryState(
                                        name = item.name ?: "No name",
                                        description = item.description ?: "No description",
                                        forksNumber = item.numberOfForks ?: 0,
                                        owner = item.owner?.login
                                    )
                                }

                                is Data.User -> {
                                    SearchListItemState.UserState(
                                        name = item.login ?: "No name",
                                        avatarURL = item.avatarURL ?: "",
                                        score = item.score ?: 0f,
                                        htmlURL = item.htmlURL
                                    )
                                }

                                else -> {
                                    null
                                }
                            }


                        }, status = Resource.Status.SUCCESS)
                    }
                }

                Resource.Status.ERROR -> {
                    _searchListState.value = SearchScreenListState(
                        list = emptyList(),
                        status = Resource.Status.ERROR
                    )
                }

                Resource.Status.LOADING -> {
                    _searchListState.value = SearchScreenListState(
                        list = emptyList(),
                        status = Resource.Status.LOADING
                    )
                }
            }

        }.launchIn(viewModelScope)
    }

}
package com.example.githubsearchapp.presentation.searchScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.domain.usecases.GetUsersUseCase
import com.example.githubsearchapp.presentation.searchScreen.state.SearchScreenListState
import com.example.githubsearchapp.presentation.searchScreen.state.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) :
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
            getUsersUseCase(searchInput = _searchInputState.value).map { resource ->
                resource.data?.let {
                    SearchScreenListState(list = it.map { user ->
                        UserState(
                            name = user.login ?: "No name",
                            avatarURL = user.avatarUrl ?: "",
                            score = user.score ?: 0f
                        )
                    }, status = resource.status)
                }
            }.collect {
                if (it != null) {
                    _searchListState.value = it
                }
            }
        }
    }
}
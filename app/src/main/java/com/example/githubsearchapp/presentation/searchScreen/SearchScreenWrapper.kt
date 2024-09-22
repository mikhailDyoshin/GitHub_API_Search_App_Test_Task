package com.example.githubsearchapp.presentation.searchScreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubsearchapp.presentation.destinations.RepositoryScreenWrapperDestination
import com.example.githubsearchapp.presentation.navigation.AppNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@AppNavGraph(start = true)
@Destination
@Composable
fun SearchScreenWrapper(
    navigator: DestinationsNavigator,
    viewModel: SearchScreenViewModel = hiltViewModel()
) {

    SearchScreen(
        searchInputState = viewModel.searchInputState.value,
        state = viewModel.searchListState.value,
        updateSearchInput = { viewModel.updateSearchInput(it) },
        onSearch = { viewModel.searchData() },
        navigateToRepositoryContent = {
            navigator.navigate(
                RepositoryScreenWrapperDestination(owner = it.owner, repository = it.repositoryName, path = "")
            )
        }

    )

}
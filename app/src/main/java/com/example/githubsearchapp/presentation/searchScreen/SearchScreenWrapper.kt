package com.example.githubsearchapp.presentation.searchScreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubsearchapp.presentation.navigation.AppNavGraph
import com.ramcosta.composedestinations.annotation.Destination

@AppNavGraph(start = true)
@Destination
@Composable
fun SearchScreenWrapper(viewModel: SearchScreenViewModel = hiltViewModel()) {

    SearchScreen(
        searchInputState = viewModel.searchInputState.value,
        state = viewModel.searchListState.value,
        updateSearchInput = { viewModel.updateSearchInput(it) },
        onSearch = { viewModel.searchData() })

}
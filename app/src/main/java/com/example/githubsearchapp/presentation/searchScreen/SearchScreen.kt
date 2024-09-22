package com.example.githubsearchapp.presentation.searchScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.githubsearchapp.presentation.navigation.navTypes.RepositoryNavData
import com.example.githubsearchapp.presentation.searchScreen.components.SearchField
import com.example.githubsearchapp.presentation.searchScreen.components.SearchScreenList
import com.example.githubsearchapp.presentation.searchScreen.state.SearchScreenListState

@Composable
fun SearchScreen(
    searchInputState: String,
    state: SearchScreenListState,
    navigateToRepositoryContent: (RepositoryNavData) -> Unit,
    updateSearchInput: (searchInput: String) -> Unit,
    onSearch: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchField(
            text = searchInputState,
            status = state.status,
            updateSearchInput = { updateSearchInput(it) },
            search = { onSearch() })
        SearchScreenList(
            state = state,
            navigateToRepositoryContent = { navigateToRepositoryContent(it) },
            onRetry = { onSearch() }
        )
    }
}
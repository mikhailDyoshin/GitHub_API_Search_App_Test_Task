package com.example.githubsearchapp.presentation.searchScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.githubsearchapp.presentation.searchScreen.components.SearchField
import com.example.githubsearchapp.presentation.searchScreen.components.SearchScreenList
import com.example.githubsearchapp.presentation.searchScreen.state.SearchScreenListState

@Composable
fun SearchScreen(
    searchInputState: String,
    state: SearchScreenListState,
    updateSearchInput: (searchInput: String) -> Unit,
    onSearch: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchField(
            text = searchInputState,
            updateSearchInput = { updateSearchInput(it) },
            search = { onSearch() })
        SearchScreenList(state)
    }
}
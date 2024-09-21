package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.presentation.searchScreen.state.SearchListItemState
import com.example.githubsearchapp.presentation.searchScreen.state.SearchScreenListState

@Composable
fun SearchScreenList(state: SearchScreenListState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (state.status) {
            Resource.Status.SUCCESS -> {
                LazyColumn {
                    items(state.list) { itemState ->
                        when (itemState) {
                            is SearchListItemState.RepositoryState -> RepositoryItem(state = itemState)
                            is SearchListItemState.UserState -> UserItem(itemState)
                            null -> {
                                // Do nothing
                            }
                        }
                    }
                }
            }

            Resource.Status.ERROR -> {
                Text(text = "Error", fontSize = 42.sp)
            }

            Resource.Status.LOADING -> {
                Text(text = "Loading...", fontSize = 42.sp)
            }
        }
    }

}

@Preview
@Composable
fun SearchScreenListLoadingPreview() {
    SearchScreenList(
        state = SearchScreenListState(
            list = emptyList(),
            status = Resource.Status.LOADING
        )
    )
}

@Preview
@Composable
fun SearchScreenListErrorPreview() {
    SearchScreenList(
        state = SearchScreenListState(
            list = emptyList(),
            status = Resource.Status.ERROR
        )
    )
}

@Preview
@Composable
fun SearchScreenLisSuccessPreview() {
    Column(modifier = Modifier.background(color = Color.White)) {
        SearchScreenList(
            state = SearchScreenListState(
                list = listOf(
                    SearchListItemState.UserState(name = "Jack", avatarURL = "", score = 145f),
                    SearchListItemState.RepositoryState(
                        name = "Tetris",
                        description = "Tetris game (my favorite)"
                    ),
                    SearchListItemState.UserState(name = "Duck", avatarURL = "", score = 1f),
                    SearchListItemState.UserState(name = "Bob", avatarURL = "", score = 2f),
                ),
                status = Resource.Status.SUCCESS
            )
        )
    }

}
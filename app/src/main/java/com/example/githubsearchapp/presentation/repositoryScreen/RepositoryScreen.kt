package com.example.githubsearchapp.presentation.repositoryScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.presentation.common.ErrorScreen
import com.example.githubsearchapp.presentation.common.LoadingIndicator
import com.example.githubsearchapp.presentation.repositoryScreen.components.RepositoryContentList
import com.example.githubsearchapp.presentation.repositoryScreen.state.RepositoryContentItemState
import com.example.githubsearchapp.presentation.repositoryScreen.state.RepositoryContentState

@Composable
fun RepositoryScreen(
    state: RepositoryContentState,
    onItemClick: (item: RepositoryContentItemState) -> Unit,
    onRetry: () -> Unit,
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (state.status) {
            Resource.Status.SUCCESS -> RepositoryContentList(
                state = state,
                onItemClick = { item ->
                    onItemClick(item)
                },
            )

            Resource.Status.ERROR -> ErrorScreen(
                errorMessage = state.message,
                onRetry = {
                    onRetry()
                }
            )

            Resource.Status.LOADING -> LoadingIndicator()
        }

    }
}
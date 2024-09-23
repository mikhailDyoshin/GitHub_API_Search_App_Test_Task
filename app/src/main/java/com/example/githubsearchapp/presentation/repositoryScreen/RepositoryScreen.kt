package com.example.githubsearchapp.presentation.repositoryScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.presentation.common.ErrorScreen
import com.example.githubsearchapp.presentation.common.LoadingIndicator
import com.example.githubsearchapp.presentation.repositoryScreen.components.RepositoryContentList
import com.example.githubsearchapp.presentation.repositoryScreen.state.RepositoryContentItemState
import com.example.githubsearchapp.presentation.repositoryScreen.state.RepositoryContentState
import com.example.githubsearchapp.presentation.repositoryScreen.state.RequestDataState
import com.example.githubsearchapp.ui.theme.RepositoryPathBarBackgroundColor

@Composable
fun RepositoryScreen(
    requestDataState: RequestDataState,
    state: RepositoryContentState,
    onItemClick: (item: RepositoryContentItemState) -> Unit,
    onRetry: () -> Unit,
) {
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = RepositoryPathBarBackgroundColor)
                    .padding(horizontal = 10.dp, vertical = 5.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${requestDataState.owner}/${requestDataState.repository}/${requestDataState.path}",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding),
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

}
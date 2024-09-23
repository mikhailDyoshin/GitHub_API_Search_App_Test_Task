package com.example.githubsearchapp.presentation.repositoryScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubsearchapp.common.RepositoryContentItemType
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.presentation.common.ErrorScreen
import com.example.githubsearchapp.presentation.common.LoadingIndicator
import com.example.githubsearchapp.presentation.destinations.RepositoryScreenWrapperDestination
import com.example.githubsearchapp.presentation.destinations.WebViewWrapperDestination
import com.example.githubsearchapp.presentation.repositoryScreen.components.RepositoryContentList
import com.example.githubsearchapp.presentation.repositoryScreen.state.RequestDataState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun RepositoryScreenWrapper(
    owner: String,
    repository: String,
    path: String,
    navigator: DestinationsNavigator,
    viewModel: RepositoryScreenViewModel = hiltViewModel()
) {

    val requestDataState = RequestDataState(
        owner = owner,
        repository = repository,
        path = path
    )

    LaunchedEffect(true) {
        getContents(viewModel = viewModel, requestDataState = requestDataState)
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (viewModel.repositoryContentState.value.status) {
            Resource.Status.SUCCESS -> RepositoryContentList(
                state = viewModel.repositoryContentState.value,
                onItemClick = { item ->
                    when (item.type) {
                        RepositoryContentItemType.FILE -> {
                            if (item.htmlURL != null) {
                                navigator.navigate(WebViewWrapperDestination(url = item.htmlURL))
                            }
                        }

                        RepositoryContentItemType.DIR -> {
                            navigator.navigate(
                                RepositoryScreenWrapperDestination(
                                    owner = owner,
                                    repository = repository,
                                    path = item.path ?: ""
                                )
                            )
                        }

                        null -> {
                            // Do nothing
                        }
                    }
                },
            )

            Resource.Status.ERROR -> ErrorScreen(
                errorMessage = viewModel.repositoryContentState.value.message,
                onRetry = {
                    getContents(viewModel = viewModel, requestDataState = requestDataState)
                }
            )

            Resource.Status.LOADING -> LoadingIndicator()
        }

    }
}

private fun getContents(viewModel: RepositoryScreenViewModel, requestDataState: RequestDataState) {
    viewModel.getRepositoryContents(
        requestData = requestDataState
    )
}
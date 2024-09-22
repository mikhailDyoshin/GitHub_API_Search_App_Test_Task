package com.example.githubsearchapp.presentation.repositoryScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubsearchapp.presentation.destinations.RepositoryScreenWrapperDestination
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

    LaunchedEffect(true) {
        viewModel.getRepositoryContents(
            requestData = RequestDataState(
                owner = owner,
                repository = repository,
                path = path
            )
        )
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        LazyColumn {
            items(viewModel.repositoryContentState.value.repositoryContent) { item ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            navigator.navigate(
                                RepositoryScreenWrapperDestination(
                                    owner = owner,
                                    repository = repository,
                                    path = item.path ?: ""
                                )
                            )
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(text = item.type.toString(), modifier = Modifier.weight(1f))
                    Text(text = item.name ?: "", modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
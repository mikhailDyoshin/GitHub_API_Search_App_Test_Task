package com.example.githubsearchapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.githubsearchapp.presentation.searchScreen.SearchScreen
import com.example.githubsearchapp.presentation.searchScreen.SearchScreenViewModel
import com.example.githubsearchapp.ui.theme.GitHubSearchAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SearchScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        setContent {
            GitHubSearchAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        SearchScreen(
                            searchInputState = viewModel.searchInputState.value,
                            state = viewModel.searchListState.value,
                            updateSearchInput = { viewModel.updateSearchInput(it) },
                            onSearch = { viewModel.searchUsers() }
                        )
                    }
                }
            }
        }
    }
}

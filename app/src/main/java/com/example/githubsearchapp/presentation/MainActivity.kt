package com.example.githubsearchapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.example.githubsearchapp.presentation.searchScreen.NavGraphs
import com.example.githubsearchapp.presentation.searchScreen.SearchScreenViewModel
import com.example.githubsearchapp.ui.theme.GitHubSearchAppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: SearchScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        setContent {
            GitHubSearchAppTheme {
                Surface {
                    DestinationsNavHost(navGraph = NavGraphs.root)

                }
//                        SearchScreen(
//                            searchInputState = viewModel.searchInputState.value,
//                            state = viewModel.searchListState.value,
//                            updateSearchInput = { viewModel.updateSearchInput(it) },
//                            onSearch = { viewModel.searchData() }
//                        )
            }
        }
    }
}

package com.example.githubsearchapp.presentation.repositoryScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.githubsearchapp.common.RepositoryContentItemType
import com.example.githubsearchapp.presentation.repositoryScreen.state.RepositoryContentItemState
import com.example.githubsearchapp.presentation.repositoryScreen.state.RepositoryContentState

@Composable
fun RepositoryContentList(
    state: RepositoryContentState,
    onItemClick: (item: RepositoryContentItemState) -> Unit,
) {
    LazyColumn {
        items(state.repositoryContent) { item ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        when (item.type) {
                            RepositoryContentItemType.FILE -> {
                                if (item.htmlURL != null) {
                                    onItemClick(item)
                                }
                            }

                            RepositoryContentItemType.DIR -> {
                                onItemClick(item)
                            }

                            null -> {
                                // Do nothing
                            }
                        }

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
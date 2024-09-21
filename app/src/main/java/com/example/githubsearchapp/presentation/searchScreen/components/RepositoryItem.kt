package com.example.githubsearchapp.presentation.searchScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubsearchapp.presentation.searchScreen.state.SearchListItemState

@Composable
fun RepositoryItem(state: SearchListItemState.RepositoryState) {
    Column(
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(4.dp))
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = state.name, fontSize = 20.sp)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = state.forksNumber.toString(), fontSize = 16.sp)
                Text(text = "Forks", fontSize = 16.sp)
            }
        }
        Text(
            text = state.description,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Gray,
            fontSize = 20.sp
        )
    }
}

@Preview
@Composable
fun RepositoryItemPreview() {
    Column(modifier = Modifier.background(color = Color.Gray)) {
        RepositoryItem(
            state = SearchListItemState.RepositoryState(
                name = "Rust-Tutorial",
                description = "Repository to study Rust programming language",
                forksNumber = 15
            )
        )
    }
}
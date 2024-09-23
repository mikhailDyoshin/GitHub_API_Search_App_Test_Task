package com.example.githubsearchapp.presentation.repositoryScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RepositoryContentItem(icon: Int, onClick: () -> Unit) {
    Column(Modifier.padding(vertical = 5.dp).clickable { onClick() }) {
        Row {

        }
        HorizontalDivider(thickness = 2.dp, color = Color.Gray)
    }
}
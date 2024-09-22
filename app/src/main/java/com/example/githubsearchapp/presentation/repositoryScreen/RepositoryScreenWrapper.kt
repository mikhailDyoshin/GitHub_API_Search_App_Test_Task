package com.example.githubsearchapp.presentation.repositoryScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.githubsearchapp.presentation.navigation.navTypes.RepositoryNavData
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun RepositoryScreenWrapper(owner: String, repository: String) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(text = owner)
        Text(text = repository)
    }
}
package com.example.githubsearchapp.presentation.repositoryScreen.state

import com.example.githubsearchapp.common.Resource

data class RepositoryContentState(val repositoryContent: List<String>, val status: Resource.Status)

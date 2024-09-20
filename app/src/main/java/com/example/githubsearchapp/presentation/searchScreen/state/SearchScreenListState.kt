package com.example.githubsearchapp.presentation.searchScreen.state

import com.example.githubsearchapp.common.Resource

data class SearchScreenListState(val list: List<UserState>, val status: Resource.Status)

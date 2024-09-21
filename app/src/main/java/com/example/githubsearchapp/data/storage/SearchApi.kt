package com.example.githubsearchapp.data.storage

import com.example.githubsearchapp.data.storage.models.SearchRepositoriesResponseModel
import com.example.githubsearchapp.data.storage.models.SearchUsersResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search/users")
    suspend fun getUsers(
        @Query("q") searchInput: String
    ): Response<SearchUsersResponseModel>

    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") name: String
    ): Response<SearchRepositoriesResponseModel>
}
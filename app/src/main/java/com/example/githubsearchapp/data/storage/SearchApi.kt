package com.example.githubsearchapp.data.storage

import com.example.githubsearchapp.data.storage.models.SearchUsersResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {

    @GET("search/users")
    suspend fun getUsers(
        @Query("q") searchInput: String
    ): Response<SearchUsersResponseModel>

}
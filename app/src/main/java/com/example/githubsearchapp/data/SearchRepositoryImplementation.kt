package com.example.githubsearchapp.data

import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.data.storage.SearchApi
import com.example.githubsearchapp.data.storage.models.UserModel
import com.example.githubsearchapp.domain.SearchRepository
import com.example.githubsearchapp.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SearchRepositoryImplementation @Inject constructor(private val searchApi: SearchApi) :
    SearchRepository {

    override fun getUsers(searchInput: String): Flow<Resource<List<User>>> = flow {
        try {
            emit(Resource.loading())

            val response = searchApi.getUsers(searchInput = searchInput)


            if (response.isSuccessful) {

                val responseBody = response.body()

                if (responseBody != null) {
                    emit(Resource.success(data = responseBody.items.map { userModelToUser(it) }))
                }

            } else {
                when (response.code()) {
                    401 -> emit(Resource.error(error = Resource.Error.ERROR_401))
                    403 -> emit(Resource.error(error = Resource.Error.ERROR_403))
                    404 -> emit(Resource.error(error = Resource.Error.ERROR_404))
                    500 -> emit(Resource.error(error = Resource.Error.ERROR_500))
                    else -> emit(Resource.error(error = Resource.Error.ERROR_UNDEFINED))
                }
            }


        } catch (e: IOException) {
            emit(Resource.error(error = Resource.Error.ERROR_NO_INTERNET_CONNECTION))
        }
    }

    private fun userModelToUser(userModel: UserModel): User {
        return User(
            login = userModel.login,
            avatarUrl = userModel.avatarUrl,
            score = userModel.score
        )
    }


}
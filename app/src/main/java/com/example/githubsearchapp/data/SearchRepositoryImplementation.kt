package com.example.githubsearchapp.data

import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.data.storage.SearchApi
import com.example.githubsearchapp.data.storage.models.RepositoryModel
import com.example.githubsearchapp.data.storage.models.UserModel
import com.example.githubsearchapp.domain.SearchRepository
import com.example.githubsearchapp.domain.models.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SearchRepositoryImplementation @Inject constructor(private val searchApi: SearchApi) :
    SearchRepository {

    override fun getData(searchInput: String): Flow<Resource<List<Any>>> = flow {
        try {
            emit(Resource.loading())

            val usersResponse = searchApi.getUsers(searchInput = searchInput)
            val repositoriesResponse = searchApi.getRepositories(name = searchInput)


            if (usersResponse.isSuccessful && repositoriesResponse.isSuccessful) {

                val usersResponseBody = usersResponse.body()
                val repositoriesResponseBody = repositoriesResponse.body()

                if (usersResponseBody != null && repositoriesResponseBody != null) {

                    val usersData: List<Data.User> = usersResponseBody.items.map {
                        userModelToUser(it)
                    }.toMutableList()

                    val repositoriesData: List<Data.Repository> =
                        repositoriesResponseBody.items.map {
                            repositoryModelToRepository(it)
                        }

                    val data = usersData + repositoriesData

                    emit(Resource.success(data = data))
                }
            } else {
                when (usersResponse.code()) {
                    401 -> emit(Resource.error(error = Resource.Error.ERROR_401))
                    403 -> emit(Resource.error(error = Resource.Error.ERROR_403))
                    404 -> emit(Resource.error(error = Resource.Error.ERROR_404))
                    500 -> emit(Resource.error(error = Resource.Error.ERROR_500))
                    else -> emit(Resource.error(error = Resource.Error.ERROR_UNDEFINED))
                }
                when (repositoriesResponse.code()) {
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

    private fun userModelToUser(userModel: UserModel): Data.User {
        return Data.User(
            login = userModel.login,
            avatarURL = userModel.avatarUrl,
            score = userModel.score,
            htmlURL = userModel.htmlURL
        )
    }

    private fun repositoryModelToRepository(repository: RepositoryModel): Data.Repository {
        return Data.Repository(
            name = repository.name,
            description = repository.description,
            numberOfForks = repository.numberOfForks
        )
    }

}
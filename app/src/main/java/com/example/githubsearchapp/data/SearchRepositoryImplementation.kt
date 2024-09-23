package com.example.githubsearchapp.data

import com.example.githubsearchapp.common.RepositoryContentItemType
import com.example.githubsearchapp.common.Resource
import com.example.githubsearchapp.common.Resource.Companion.mapHttpErrorCodeToResourceError
import com.example.githubsearchapp.common.utils.handleNetworkError
import com.example.githubsearchapp.data.storage.SearchApi
import com.example.githubsearchapp.data.storage.models.RepositoryContentItemModel
import com.example.githubsearchapp.data.storage.models.RepositoryModel
import com.example.githubsearchapp.data.storage.models.UserModel
import com.example.githubsearchapp.domain.SearchRepository
import com.example.githubsearchapp.domain.models.Data
import com.example.githubsearchapp.domain.models.RepositoryContentItem
import com.example.githubsearchapp.domain.models.RepositoryContentRequestData
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
                mapHttpErrorCodeToResourceError(usersResponse.code())
                mapHttpErrorCodeToResourceError(repositoriesResponse.code())
            }
        } catch (e: IOException) {
            emit(Resource.error(error = Resource.Error.ERROR_NO_INTERNET_CONNECTION))
        } catch (e: Exception) {
            handleNetworkError(e)
        }
    }

    override fun getRepositoryContents(requestData: RepositoryContentRequestData): Flow<Resource<List<RepositoryContentItem>>> =
        flow {
            try {

                val response = searchApi.getRepositoryContents(
                    owner = requestData.owner,
                    repository = requestData.repository,
                    path = requestData.path
                )

                if (response.isSuccessful) {
                    val responseBody = response.body()

                    if (responseBody != null) {
                        val repositoryContents =
                            responseBody.map { repositoryContentItemModelToRepositoryContentItem(it) }

                        val sortedRepositoryContents = sortRepositoryContents(repositoryContents)

                        emit(Resource.success(data = sortedRepositoryContents))
                    }
                } else {
                    mapHttpErrorCodeToResourceError(response.code())
                }

            } catch (e: IOException) {
                emit(Resource.error(error = Resource.Error.ERROR_NO_INTERNET_CONNECTION))
            } catch (e: Exception) {
                handleNetworkError(e)
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
            numberOfForks = repository.numberOfForks,
            owner = repository.owner?.let { userModelToUser(it) }
        )
    }

    private fun repositoryContentItemModelToRepositoryContentItem(repositoryContentItemModel: RepositoryContentItemModel): RepositoryContentItem {
        return RepositoryContentItem(
            name = repositoryContentItemModel.name,
            type = getRepositoryContentItemType(repositoryContentItemModel),
            path = repositoryContentItemModel.path,
            htmlURL = repositoryContentItemModel.htmlURL
        )
    }

    private fun getRepositoryContentItemType(repositoryContentItemModel: RepositoryContentItemModel): RepositoryContentItemType? {
        return when (repositoryContentItemModel.type) {
            "file" -> RepositoryContentItemType.FILE
            "dir" -> RepositoryContentItemType.DIR
            else -> null
        }
    }

    private fun sortRepositoryContents(repositoryContent: List<RepositoryContentItem?>): List<RepositoryContentItem> {
        return repositoryContent.filterNotNull()
            .sortedWith(compareBy { it.type == RepositoryContentItemType.FILE })
    }

}
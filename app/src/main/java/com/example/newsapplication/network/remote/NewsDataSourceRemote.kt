package com.example.newsapplication.network.remote

import com.example.newsapplication.network.api.ApiInterface
import com.example.newsapplication.models.NetworkResult
import com.example.newsapplication.models.TopStories
import com.example.newsapplication.network.repository.NewsDataSource
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsDataSourceRemote @Inject constructor(
    private val apiInterface: ApiInterface,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NewsDataSource {
    override suspend fun getTasks(): NetworkResult<TopStories> {
        var output: NetworkResult<TopStories> = NetworkResult.Loading()

        withContext(ioDispatcher) {
            try {
                val response = apiInterface.getTopStories()
                if (response.isSuccessful) {
                    var json = Gson().toJson(response.body())
                    if (response.body()?.results?.size!! <= 0) {

                    } else {
                        val result = response.body()?.copyright
                        output = NetworkResult.Success(response.body()!!)
                    }
                } else {
                    output =
                        NetworkResult.Error(code = response.code(), message = response.message())
                }
            } catch (Ex: Exception) {
                Ex.localizedMessage?.let { println("$it") }
                output = NetworkResult.Exception(Ex)
            }
        }

        return output

    }
}
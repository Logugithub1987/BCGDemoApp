package com.example.newsapplication.network.repository

import com.example.newsapplication.di.AppModule
import com.example.newsapplication.models.NetworkResult
import com.example.newsapplication.models.TopStories
import javax.inject.Inject

class DefaultNewsRepo @Inject constructor(
    @AppModule.NewsDataSourceRemote private val newsDataSourceRemote: NewsDataSource,
    @AppModule.NewsDataSourceLocal private val newsDataSourceLocal: NewsDataSource,
) : NewsRepository {
    override suspend fun getTasks(forceUpdate: Boolean): NetworkResult<TopStories> {
        val output: NetworkResult<TopStories> = if (forceUpdate) {
            newsDataSourceRemote.getTasks()
        } else {
            newsDataSourceLocal.getTasks()
            NetworkResult.Error(3, "DB DATA")
        }
        return output
    }
}
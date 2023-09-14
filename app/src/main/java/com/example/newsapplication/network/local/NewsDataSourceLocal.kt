package com.example.newsapplication.network.local

import com.example.newsapplication.models.NetworkResult
import com.example.newsapplication.models.TopStories
import com.example.newsapplication.network.repository.NewsDataSource
import javax.inject.Inject

class NewsDataSourceLocal @Inject constructor(): NewsDataSource {
    override suspend fun getTasks(): NetworkResult<TopStories> {
        TODO("Not yet implemented")
    }
}
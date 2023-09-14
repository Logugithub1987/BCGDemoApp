package com.example.newsapplication.network.repository

import com.example.newsapplication.models.NetworkResult
import com.example.newsapplication.models.TopStories

interface NewsRepository {
    suspend fun getTasks(forceUpdate: Boolean = false): NetworkResult<TopStories>
}
package com.example.newsapplication.network.repository

import com.example.newsapplication.models.NetworkResult
import com.example.newsapplication.models.TopStories

interface NewsDataSource {
    suspend fun getTasks(): NetworkResult<TopStories>
}
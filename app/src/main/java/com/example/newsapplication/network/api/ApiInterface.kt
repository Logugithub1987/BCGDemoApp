package com.example.newsapplication.network.api

import com.example.newsapplication.models.TopStories
import com.example.newsapplication.utils.Constant
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("svc/topstories/v2/world.json?api-key="+Constant.API_KEY)
    suspend fun getTopStories(): Response<TopStories>
}
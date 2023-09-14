package com.example.newsapplication.newslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.models.NetworkResult
import com.example.newsapplication.models.Result
import com.example.newsapplication.models.TopStories
import com.example.newsapplication.network.repository.NewsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsListViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel()  {

    var newsListResponse: MutableLiveData<NetworkResult<TopStories>> = MutableLiveData()

    val data: LiveData<List<Result>>
        get() = _data
    private val _data = MutableLiveData<List<Result>>(emptyList())

    lateinit var tempResults : List<Result>

    fun getTopNews(){
        newsListResponse.value = NetworkResult.Loading()
        viewModelScope.launch {
            newsListResponse.value = newsRepository.getTasks(true)
        }
    }
}
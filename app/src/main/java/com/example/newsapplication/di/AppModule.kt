package com.example.newsapplication.di

import android.content.Context
import com.example.newsapplication.NewsApp
import com.example.newsapplication.network.repository.NewsDataSource
import com.example.newsapplication.network.api.ApiInterface
import com.example.newsapplication.network.remote.NewsDataSourceRemote
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module(includes = [ViewModelsModule::class , NetworkModule::class])
class AppModule() {

    @Singleton
    @Provides
    fun provideContext(newsApp: NewsApp): Context = newsApp

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class NewsDataSourceRemote

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class NewsDataSourceLocal

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

    @Singleton
    @NewsDataSourceRemote
    @Provides
    fun provideNewsDataSourceRemote(
        apiInterface: ApiInterface,
        ioDispatcher: CoroutineDispatcher): NewsDataSource {
        return NewsDataSourceRemote (apiInterface,ioDispatcher)
    }

    @Singleton
    @NewsDataSourceLocal
    @Provides
    fun provideTasksLocalDataSource(): NewsDataSource {
        return com.example.newsapplication.network.local.NewsDataSourceLocal()
    }

}
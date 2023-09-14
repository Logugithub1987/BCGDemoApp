package com.example.newsapplication.di

import com.example.newsapplication.network.repository.DefaultNewsRepo
import com.example.newsapplication.network.repository.NewsRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModuleBinds {
    @Singleton
    @Binds
    abstract fun bindRepository(repo: DefaultNewsRepo): NewsRepository
}

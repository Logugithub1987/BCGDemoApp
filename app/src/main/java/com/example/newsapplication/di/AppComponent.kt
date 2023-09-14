package com.example.newsapplication.di

import android.content.Context
import com.example.newsapplication.newsdetails.NewsDetailFragment
import com.example.newsapplication.newslist.NewsListFragment
import com.example.newsapplication.newslist.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AppModuleBinds::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: NewsListFragment)
    fun inject(fragment: NewsDetailFragment)
    fun inject(activity: MainActivity)
}
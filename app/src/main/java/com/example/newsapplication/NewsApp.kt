package com.example.newsapplication

import android.app.Application

import com.example.newsapplication.di.DaggerAppComponent
import com.example.newsapplication.di.AppComponent

open class NewsApp : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {

        return DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
    }

}
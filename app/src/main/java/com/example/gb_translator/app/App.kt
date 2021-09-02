package com.example.gb_translator.app

import android.app.Application
import com.example.gb_translator.di.AppComponent
import com.example.gb_translator.di.DaggerAppComponent
import com.example.gb_translator.di.module.AppModule

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

}

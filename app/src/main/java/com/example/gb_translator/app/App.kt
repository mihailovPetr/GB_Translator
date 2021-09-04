package com.example.gb_translator.app

import android.app.Application
import com.example.gb_translator.di.application
import com.example.gb_translator.di.mainScreen
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}

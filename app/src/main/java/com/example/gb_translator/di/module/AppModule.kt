package com.example.gb_translator.di.module

import com.example.gb_translator.app.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

}
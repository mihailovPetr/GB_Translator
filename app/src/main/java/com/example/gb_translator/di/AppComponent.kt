package com.example.gb_translator.di

import com.example.gb_translator.di.module.AppModule
import com.example.gb_translator.di.module.RepositoryModule
import com.example.gb_translator.di.module.ViewModelModule
import com.example.gb_translator.view.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
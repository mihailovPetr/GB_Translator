package com.example.gb_translator.di

import com.example.gb_translator.model.data.DataModel
import com.example.gb_translator.model.datasource.RetrofitImpl
import com.example.gb_translator.model.datasource.RoomDataBaseImpl
import com.example.gb_translator.model.repository.Repository
import com.example.gb_translator.model.repository.RepositoryImpl
import com.example.gb_translator.view.main.MainInteractor
import com.example.gb_translator.view.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) { RepositoryImpl(RetrofitImpl()) }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) { RepositoryImpl(RoomDataBaseImpl()) }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}
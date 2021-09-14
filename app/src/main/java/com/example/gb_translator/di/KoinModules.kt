package com.example.gb_translator.di

import androidx.room.Room
import com.example.repository.api.ApiHolder
import com.example.repository.cache.Cache
import com.example.repository.cache.ICache
import com.example.repository.repository.IRepository
import com.example.repository.repository.Repository
import com.example.repository.room.HistoryDataBase
import com.example.gb_translator.view.history.HistoryInteractor
import com.example.gb_translator.view.history.HistoryViewModel
import com.example.gb_translator.view.main.MainInteractor
import com.example.gb_translator.view.main.MainViewModel
import com.example.repository.networkStatus.AndroidNetworkStatus
import com.example.repository.networkStatus.INetworkStatus
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val application = module {
    single<INetworkStatus> { AndroidNetworkStatus(get()) }
    single { ApiHolder().api }
    single {
        Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<HistoryDataBase>().historyDao() }
    single<ICache> { Cache(get()) }
    single<IRepository> { Repository(get(), get(), get()) }
}

val mainScreen = module {
    factory { MainInteractor(get()) }
    viewModel { MainViewModel(get()) }
}

val historyScreen = module {
    viewModel { HistoryViewModel(get()) }
    factory { HistoryInteractor(get()) }
}
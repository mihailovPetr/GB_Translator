package com.example.gb_translator.di

import androidx.room.Room
import com.example.gb_translator.model.data.DataModel
import com.example.gb_translator.model.datasource.RetrofitImpl
import com.example.gb_translator.model.datasource.RoomDataBaseImpl
import com.example.gb_translator.model.repository.Repository
import com.example.gb_translator.model.repository.RepositoryImpl
import com.example.gb_translator.model.repository.RepositoryImplementationLocal
import com.example.gb_translator.model.repository.RepositoryLocal
import com.example.gb_translator.room.HistoryDataBase
import com.example.gb_translator.view.history.HistoryInteractor
import com.example.gb_translator.view.history.HistoryViewModel
import com.example.gb_translator.view.main.MainInteractor
import com.example.gb_translator.view.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single { Room.databaseBuilder(get(), HistoryDataBase::class.java, "HistoryDB").build() }
    single { get<HistoryDataBase>().historyDao() }
    single<Repository<List<DataModel>>> { RepositoryImpl(RetrofitImpl()) }
    single<RepositoryLocal<List<DataModel>>> { RepositoryImplementationLocal(RoomDataBaseImpl(get()))}
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    viewModel { MainViewModel(get()) }
}

val historyScreen = module {
    viewModel { HistoryViewModel(get()) }
    factory { HistoryInteractor(get()) }
}
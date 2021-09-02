package com.example.gb_translator.di.module

import com.example.gb_translator.di.NAME_LOCAL
import com.example.gb_translator.di.NAME_REMOTE
import com.example.gb_translator.model.data.DataModel
import com.example.gb_translator.model.datasource.DataSource
import com.example.gb_translator.model.datasource.RetrofitImpl
import com.example.gb_translator.model.datasource.RoomDataBaseImpl
import com.example.gb_translator.model.repository.Repository
import com.example.gb_translator.model.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun repositoryRemote(@Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImpl(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun repositoryLocal(@Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>): Repository<List<DataModel>> =
        RepositoryImpl(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    fun dataSourceRemote(): DataSource<List<DataModel>> = RetrofitImpl()

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    fun dataSourceLocal(): DataSource<List<DataModel>> = RoomDataBaseImpl()
}

package com.example.gb_translator.model.datasource

import com.example.gb_translator.model.data.DataModel
import io.reactivex.Observable

class DataSourceLocal(private val localProvider: RoomDataBaseImpl = RoomDataBaseImpl()):
    DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> = localProvider.getData(word)
}
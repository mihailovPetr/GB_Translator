package com.example.gb_translator.model.datasource

import com.example.gb_translator.model.data.DataModel
import io.reactivex.Observable
import javax.inject.Inject

class RoomDataBaseImpl: DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("Not yet implemented")
    }
}
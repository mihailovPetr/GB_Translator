package com.example.gb_translator.model.repository

import com.example.gb_translator.model.data.DataModel
import com.example.gb_translator.model.datasource.DataSource
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImpl (private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }
}
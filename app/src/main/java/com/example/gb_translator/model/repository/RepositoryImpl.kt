package com.example.gb_translator.model.repository

import com.example.gb_translator.model.data.DataModel
import com.example.gb_translator.model.datasource.DataSource

class RepositoryImpl (private val dataSource: DataSource<List<DataModel>>) :
    Repository<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}
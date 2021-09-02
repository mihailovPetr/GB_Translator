package com.example.gb_translator.viewModel

import io.reactivex.Observable

interface Interactor<T> {
    fun getData(word:String, fromRemoteSource:Boolean):Observable<T>
}
package com.example.gb_translator.presenter

import com.example.gb_translator.view.base.View

interface Presenter<V: View> {
    fun attachView(view: V)

    fun detachView()

    fun getData(word: String, isOnline: Boolean)
}
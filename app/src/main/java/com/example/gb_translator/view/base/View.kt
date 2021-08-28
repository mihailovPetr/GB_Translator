package com.example.gb_translator.view.base

import com.example.gb_translator.model.data.AppState

interface View {
    fun renderData(appState: AppState)
}
package com.example.gb_translator.view.base

import com.example.model.entity.AppState

interface View {
    fun renderData(appState: AppState)
}
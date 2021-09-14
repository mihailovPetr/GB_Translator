package com.example.model.entity

sealed class AppState {
    data class Success<T>(val data: T) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}

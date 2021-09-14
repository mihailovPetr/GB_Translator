package com.example.gb_translator.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model.entity.AppState
import kotlinx.coroutines.*

class MainViewModel(private val interactor: MainInteractor) : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()
    private val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    private fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    fun getLiveData(): LiveData<AppState> {
        return liveData
    }

    override fun onCleared() {
        super.onCleared()
        liveData.value = AppState.Success(null)
        cancelJob()
    }

    private fun handleError(error: Throwable) {
        liveData.postValue(AppState.Error(error))
    }

    fun getData(word: String, isOnline: Boolean) {
        if (word.isNullOrEmpty()) {
            return
        }

        liveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { liveData.postValue(interactor.getData(word)) }
    }

}
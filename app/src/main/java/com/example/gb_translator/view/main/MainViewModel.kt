package com.example.gb_translator.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gb_translator.model.data.AppState
import com.example.gb_translator.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val interactor: MainInteractor,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()


    fun getLiveData(): LiveData<AppState> {
        return liveData
    }

    override fun onCleared() {
        compositeDisposable.clear()
    }

    fun getData(word: String, isOnline: Boolean) {
        if (word.isNullOrEmpty()) {
            return
        }

        interactor.getData(word, isOnline)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe { liveData.value = AppState.Loading(null) }
            .subscribe({ appState ->
                liveData.value = appState
            }, { e ->
                liveData.value = AppState.Error(e)
            })
            .also { compositeDisposable.add(it) }
    }

}
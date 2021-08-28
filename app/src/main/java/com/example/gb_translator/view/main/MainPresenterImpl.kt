package com.example.gb_translator.view.main

import com.example.gb_translator.model.data.AppState
import com.example.gb_translator.model.datasource.DataSourceLocal
import com.example.gb_translator.model.datasource.DataSourceRemote
import com.example.gb_translator.model.repository.RepositoryImpl
import com.example.gb_translator.presenter.Presenter
import com.example.gb_translator.rx.SchedulerProvider
import com.example.gb_translator.view.base.View
import io.reactivex.disposables.CompositeDisposable

class MainPresenterImpl<V : View>(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImpl(DataSourceRemote()),
        RepositoryImpl(DataSourceLocal()),
    ),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : Presenter<V> {

    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView() {
        compositeDisposable.dispose()
        currentView = null
    }

    override fun getData(word: String, isOnline: Boolean) {
        if (word.isNullOrEmpty()) {
            return
        }

        interactor.getData(word, isOnline)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
            .subscribe({ appState ->
                currentView?.renderData(appState)
            }, { e ->
                currentView?.renderData(AppState.Error(e))
            })
            .also { compositeDisposable.add(it) }
    }

}
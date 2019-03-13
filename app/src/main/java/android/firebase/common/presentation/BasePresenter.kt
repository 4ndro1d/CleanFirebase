package android.firebase.common.presentation

import io.reactivex.disposables.CompositeDisposable

interface BasePresenter<V> {

    val disposables: CompositeDisposable
        get() = CompositeDisposable()

    fun start(view: V)

    fun stop() {
        disposables.clear()
    }
}
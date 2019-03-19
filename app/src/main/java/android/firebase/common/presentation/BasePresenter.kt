package android.firebase.common.presentation

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V> {

    protected val disposables = CompositeDisposable()

    /**
     * view calles this when fully initialized (e.g. onViewCreated)
     */
    abstract fun start(view: V)

    /**
     * view calles this before it is destroyed (e.g. onViewDestroyed)
     */
    open fun stop() {
        disposables.clear()
    }
}

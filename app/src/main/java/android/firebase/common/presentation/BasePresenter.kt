package android.firebase.common.presentation

interface BasePresenter<V> {

    fun start(view: V)
}
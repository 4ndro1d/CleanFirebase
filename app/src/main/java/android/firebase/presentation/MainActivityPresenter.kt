package android.firebase.presentation

import android.firebase.common.presentation.BasePresenter

class MainActivityPresenter : BasePresenter<MainActivityView> {

    override fun start(view: MainActivityView) {
        view.doSomething()
    }
}
package android.firebase.authentication

import android.firebase.common.presentation.BasePresenter

class AuthenticationPresenter : BasePresenter<AuthenticationView> {

    override fun start(view: AuthenticationView) {
        view.startLogin()
    }
}
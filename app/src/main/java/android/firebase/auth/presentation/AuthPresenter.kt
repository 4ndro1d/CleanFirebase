package android.firebase.auth.presentation

import android.firebase.auth.domain.IsUserAuthenticatedUseCase
import android.firebase.common.presentation.BasePresenter

class AuthPresenter(
    private val isUserAuthenticatedUseCase: IsUserAuthenticatedUseCase
) : BasePresenter<AuthenticationView> {

    override fun start(view: AuthenticationView) {
        if (isUserAuthenticatedUseCase.execute()) {
            view.userAlreadyLoggedIn()
        } else {
            view.startLogin()
        }
    }
}
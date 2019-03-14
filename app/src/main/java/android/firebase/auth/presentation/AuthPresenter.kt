package android.firebase.auth.presentation

import android.firebase.auth.domain.LoadCurrentUserUseCase
import android.firebase.common.presentation.BasePresenter

class AuthPresenter(
    private val isUserAuthenticatedUseCase: LoadCurrentUserUseCase
) : BasePresenter<AuthenticationView> {

    override fun start(view: AuthenticationView) {
        if (isUserAuthenticatedUseCase.execute() != null) {
            view.userAlreadyLoggedIn()
        } else {
            view.startLogin()
        }
    }
}
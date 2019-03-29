package android.firebase.feature.user.presentation

import android.firebase.common.presentation.BasePresenter
import android.firebase.feature.user.domain.usecase.LoadAuthenticatedUserUseCase

class AuthPresenter(
    private val loadAuthenticatedUserUseCase: LoadAuthenticatedUserUseCase
) : BasePresenter<AuthenticationView>() {

    override fun start(view: AuthenticationView) {
        if (loadAuthenticatedUserUseCase.execute() != null) {
            view.userAlreadyLoggedIn()
        } else {
            view.startLogin()
        }
    }
}
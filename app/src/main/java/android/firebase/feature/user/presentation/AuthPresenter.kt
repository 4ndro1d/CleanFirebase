package android.firebase.feature.user.presentation

import android.firebase.common.presentation.BasePresenter
import android.firebase.feature.user.domain.usecase.LoadAuthenticatedUserUseCase
import android.firebase.feature.user.domain.usecase.SaveUserUseCase
import io.reactivex.rxkotlin.plusAssign

class AuthPresenter(
    private val loadAuthenticatedUserUseCase: LoadAuthenticatedUserUseCase,
    private val saveUserUseCase: SaveUserUseCase
) : BasePresenter<AuthenticationView>() {

    override fun start(view: AuthenticationView) {
        if (loadAuthenticatedUserUseCase.execute() != null) {
            view.userAlreadyLoggedIn()
        } else {
            view.startLogin()
        }
    }

    fun addUser(email: String?) {
        //TODO check if user already exists in database?
        disposables += saveUserUseCase.execute(
            SaveUserUseCase.Params(loadAuthenticatedUserUseCase.execute()?.uid, email ?: ""))
            .subscribe()
    }
}
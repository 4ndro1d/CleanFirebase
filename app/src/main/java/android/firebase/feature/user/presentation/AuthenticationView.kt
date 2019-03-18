package android.firebase.feature.user.presentation

import android.firebase.common.view.BaseView

interface AuthenticationView : BaseView {

    fun startLogin()

    fun userAlreadyLoggedIn()
}

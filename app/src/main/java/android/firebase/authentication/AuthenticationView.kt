package android.firebase.authentication

import android.firebase.common.view.BaseView

interface AuthenticationView : BaseView {
    fun startLogin()
}

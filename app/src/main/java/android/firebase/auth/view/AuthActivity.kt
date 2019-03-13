package android.firebase.auth.view

import android.app.Activity
import android.content.Intent
import android.firebase.auth.presentation.AuthPresenter
import android.firebase.auth.presentation.AuthenticationView
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.koin.android.ext.android.inject

class AuthActivity : AppCompatActivity(), AuthenticationView {

    private val presenter: AuthPresenter by inject()

    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.start(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                user = FirebaseAuth.getInstance().currentUser
            } else {
                response?.error?.errorCode?.let {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun userAlreadyLoggedIn() {
        Toast.makeText(this, "User is already logged in", Toast.LENGTH_SHORT).show()
    }

    override fun startLogin() {
        FirebaseAuth.getInstance().currentUser?.let {
            user = it
        }

        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build())

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN)
    }

    private companion object {
        const val RC_SIGN_IN = 1
    }
}

package android.firebase.feature.user.view

import android.app.Activity
import android.content.Intent
import android.firebase.R
import android.firebase.feature.user.presentation.AuthPresenter
import android.firebase.feature.user.presentation.AuthenticationView
import android.firebase.main.MainActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import org.koin.android.ext.android.inject

class AuthActivity : AppCompatActivity(), AuthenticationView {

    private val presenter: AuthPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.start(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, AuthActivity::class.java))
                navigateToMain()
            } else {
                response?.error?.errorCode?.let {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigateToMain() {
        Intent(this, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }.also {
            startActivity(it)
        }
    }

    override fun userAlreadyLoggedIn() {
        Toast.makeText(this, getString(R.string.already_logged_in), Toast.LENGTH_SHORT).show()
        navigateToMain()
    }

    override fun startLogin() {
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

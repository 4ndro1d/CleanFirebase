package android.firebase.feature.user.view

import android.app.Activity
import android.content.Intent
import android.firebase.R
import android.firebase.feature.user.presentation.AuthPresenter
import android.firebase.feature.user.presentation.AuthenticationView
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import org.koin.android.ext.android.inject

class AuthFragment : Fragment(), AuthenticationView {

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
                Toast.makeText(context, "User successfully logged in", Toast.LENGTH_SHORT).show()
                presenter.addUser(response?.email)
                navigateToMain()
            } else {
                response?.error?.let {
                    Toast.makeText(context, "Error: ${it.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun navigateToMain() {
        findNavController().navigate(R.id.action_authFragment_to_listsFragment)
    }

    override fun userAlreadyLoggedIn() {
        Toast.makeText(context, "User is already logged in", Toast.LENGTH_SHORT).show()
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

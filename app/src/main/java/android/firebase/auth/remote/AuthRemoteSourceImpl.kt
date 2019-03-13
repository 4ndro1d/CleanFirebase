package android.firebase.auth.remote

import android.firebase.auth.data.AuthRemoteSource
import com.google.firebase.auth.FirebaseAuth

class AuthRemoteSourceImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthRemoteSource {

    override fun isUserAuthenticated(): Boolean =
        firebaseAuth.currentUser != null
}
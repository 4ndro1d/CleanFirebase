package android.firebase.feature.auth.remote.repository

import android.firebase.feature.auth.data.AuthRemoteSource
import com.google.firebase.auth.FirebaseAuth

class AuthRemoteSourceImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthRemoteSource {

    override fun loadAuthenticatedUser() =
        firebaseAuth.currentUser
}
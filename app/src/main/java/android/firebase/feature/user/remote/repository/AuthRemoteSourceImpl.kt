package android.firebase.feature.user.remote.repository

import android.firebase.feature.user.data.AuthRemoteSource
import com.google.firebase.auth.FirebaseAuth

class AuthRemoteSourceImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthRemoteSource {

    override fun loadAuthenticatedUser() =
        firebaseAuth.currentUser
}
package android.firebase.feature.auth.data

import com.google.firebase.auth.FirebaseUser

interface AuthRemoteSource {

    fun loadAuthenticatedUser(): FirebaseUser?
}
package android.firebase.feature.user.data

import com.google.firebase.auth.FirebaseUser

interface AuthRemoteSource {

    fun loadAuthenticatedUser(): FirebaseUser?
}
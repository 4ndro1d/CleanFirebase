package android.firebase.auth.data

import com.google.firebase.auth.FirebaseUser

interface AuthRemoteSource {

    fun loadCurrentUser(): FirebaseUser?
}
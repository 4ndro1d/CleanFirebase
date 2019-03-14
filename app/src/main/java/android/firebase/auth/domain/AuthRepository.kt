package android.firebase.auth.domain

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    fun loadCurrentUser(): FirebaseUser?
}
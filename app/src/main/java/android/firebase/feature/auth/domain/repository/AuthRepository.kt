package android.firebase.feature.auth.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    fun loadAuthenticatedUser(): FirebaseUser?
}
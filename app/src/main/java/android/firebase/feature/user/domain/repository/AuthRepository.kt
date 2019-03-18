package android.firebase.feature.user.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {

    fun loadAuthenticatedUser(): FirebaseUser?
}
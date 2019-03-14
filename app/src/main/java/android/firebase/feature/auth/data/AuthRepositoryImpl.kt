package android.firebase.feature.auth.data

import android.firebase.feature.auth.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val remoteSource: AuthRemoteSource
) : AuthRepository {

    override fun loadAuthenticatedUser() =
        remoteSource.loadAuthenticatedUser()
}
package android.firebase.feature.user.data

import android.firebase.feature.user.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val remoteSource: AuthRemoteSource
) : AuthRepository {

    override fun loadAuthenticatedUser() =
        remoteSource.loadAuthenticatedUser()
}
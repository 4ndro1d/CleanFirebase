package android.firebase.auth.data

import android.firebase.auth.domain.AuthRepository

class AuthRepositoryImpl(
    private val remoteSource: AuthRemoteSource
) : AuthRepository {

    override fun isUserAuthenticated(): Boolean =
        remoteSource.isUserAuthenticated()
}
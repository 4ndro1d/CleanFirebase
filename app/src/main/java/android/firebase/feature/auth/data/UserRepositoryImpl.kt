package android.firebase.feature.auth.data

import android.firebase.feature.auth.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remoteSource: UserRemoteSource
) : UserRepository {

    override fun saveUser(id: String?, email: String) =
        remoteSource.saveUser(id, email)
}
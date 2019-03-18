package android.firebase.feature.user.data

import android.firebase.feature.user.domain.repository.UserRepository

class UserRepositoryImpl(
    private val remoteSource: UserRemoteSource
) : UserRepository {

    override fun saveUser(id: String?, email: String) =
        remoteSource.saveUser(id, email)
}
package android.firebase.feature.user.domain.repository

import io.reactivex.Completable

interface UserRepository {

    fun saveUser(id: String?, email: String): Completable
}
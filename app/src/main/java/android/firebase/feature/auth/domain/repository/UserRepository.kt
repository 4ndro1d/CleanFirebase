package android.firebase.feature.auth.domain.repository

import io.reactivex.Completable

interface UserRepository {

    fun saveUser(id: String?, email: String): Completable
}
package android.firebase.feature.user.domain.repository

import android.firebase.feature.user.domain.model.User
import io.reactivex.Completable
import io.reactivex.Single

interface UserRepository {

    fun saveUser(id: String?, email: String): Completable

    fun loadUserByEmail(email: String): Single<User>
}
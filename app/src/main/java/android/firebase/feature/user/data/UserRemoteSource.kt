package android.firebase.feature.user.data

import io.reactivex.Completable

interface UserRemoteSource {

    fun saveUser(id: String?, email: String): Completable
}

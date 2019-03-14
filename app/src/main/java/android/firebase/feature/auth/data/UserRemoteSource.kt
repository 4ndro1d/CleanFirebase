package android.firebase.feature.auth.data

import io.reactivex.Completable

interface UserRemoteSource {

    fun saveUser(id: String?, email: String): Completable
}

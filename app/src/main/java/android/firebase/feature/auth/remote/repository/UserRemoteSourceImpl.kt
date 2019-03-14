package android.firebase.feature.auth.remote.repository

import android.firebase.feature.auth.data.UserRemoteSource
import android.firebase.feature.auth.domain.model.User
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable

class UserRemoteSourceImpl(
    private val firestore: FirebaseFirestore
) : UserRemoteSource {

    override fun saveUser(id: String?, email: String) = Completable.fromAction {
        val userId = id ?: getNewIdForUser(firestore)
        val newUser = User(userId, email)
        firestore
            .collection("users")
            .document(userId)
            .set(newUser)
    }

    private fun getNewIdForUser(firestore: FirebaseFirestore): String {
        return firestore
            .collection("users")
            .document()
            .id
    }
}
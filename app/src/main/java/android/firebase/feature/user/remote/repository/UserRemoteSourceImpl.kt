package android.firebase.feature.user.remote.repository

import android.firebase.feature.user.data.UserRemoteSource
import android.firebase.feature.user.domain.model.User
import android.firebase.feature.user.remote.mapper.RemoteUserMapper
import android.firebase.feature.user.remote.model.RemoteUser
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.Single

class UserRemoteSourceImpl(
    private val firestore: FirebaseFirestore,
    private val userMapper: RemoteUserMapper
) : UserRemoteSource {

    override fun saveUser(id: String?, email: String) = Completable.fromAction {
        val userId = id ?: getNewIdForUser(firestore)
        val newUser = RemoteUser(userId, email)
        firestore
            .collection("users")
            .document(userId)
            .set(newUser)
    }

    private fun getNewIdForUser(firestore: FirebaseFirestore): String {
        return firestore
            .collection(COLLECTION_USERS)
            .document()
            .id
    }

    override fun loadUserByEmail(email: String): Single<User> =
        Single.create<User> { emitter ->
            firestore.collection(COLLECTION_USERS)
                .whereEqualTo("email", email)
                .limit(1)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val users = task.result?.toObjects(RemoteUser::class.java)
                        //TODO OMG
                        if (users != null && !users.isEmpty() && users[0] != null) {
                            emitter.onSuccess(userMapper.from(users[0]))
                        } else {
                            emitter.onError(NullPointerException("User not found."))
                        }
                    } else {
                        emitter.onError(NullPointerException("Server error"))
                    }
                }
        }

    private companion object {
        const val COLLECTION_USERS = "users"
    }
}
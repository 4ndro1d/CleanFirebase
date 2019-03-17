package android.firebase.feature.item.remote.repository

import android.firebase.feature.item.data.ItemRemoteSource
import android.firebase.feature.item.domain.model.Item
import android.firebase.feature.item.domain.model.ItemWithState
import android.firebase.feature.item.domain.model.STATE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.Observable

class ItemRemoteSourceImpl(
    private val firestore: FirebaseFirestore
) : ItemRemoteSource {

    override fun loadItemsForCurrentUser(): Observable<List<ItemWithState>> =
        Observable.create { emitter ->
            val listenerRegistration = firestore
                .collection(COLLECTION_ITEMS)
                //TODO inject userId
                .whereEqualTo("userId", FirebaseAuth.getInstance().currentUser?.uid)
                .limit(50)
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        emitter.onError(e)
                    } else {
                        snapshot?.documentChanges?.map {
                            when (it.type) {
                                DocumentChange.Type.ADDED -> ItemWithState(
                                    STATE.ADDED, it.document.toObject(Item::class.java))
                                DocumentChange.Type.MODIFIED -> ItemWithState(
                                    STATE.MODIFIED, it.document.toObject(Item::class.java))
                                DocumentChange.Type.REMOVED -> ItemWithState(
                                    STATE.REMOVED, it.document.toObject(Item::class.java))
                            }
                        }.let {
                            it?.let { it1 -> emitter.onNext(it1) }
                        }

//                        snapshot?.toObjects(Item::class.java)
//                            ?.let { emitter.onNext(it.map { item -> ItemWithState(STATE.ADDED, item) }) }
                    }
                }
            emitter.setCancellable { listenerRegistration.remove() }
        }

    override fun saveItem(item: Item) =
        Completable.fromAction {
            val id = firestore
                .collection(COLLECTION_ITEMS)
                .document()
                .id

            val newTodo = item.copy(id = id)

            firestore
                .collection(COLLECTION_ITEMS)
                .document(id)
                .set(newTodo)
        }

    override fun updateItem(item: Item) =
        Completable.fromAction {
            firestore.collection(COLLECTION_ITEMS)
                .document(item.id)
                .set(item)
        }

    companion object {
        const val COLLECTION_ITEMS = "items"
    }
}
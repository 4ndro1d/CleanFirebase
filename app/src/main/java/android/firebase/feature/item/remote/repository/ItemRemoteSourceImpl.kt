package android.firebase.feature.item.remote.repository

import android.firebase.feature.item.data.ItemRemoteSource
import android.firebase.feature.item.domain.model.Item
import android.firebase.feature.item.domain.model.ItemWithState
import android.firebase.feature.item.domain.model.STATE
import android.firebase.feature.item.remote.mapper.RemoteItemMapper
import android.firebase.feature.item.remote.mapper.RemoteItemWithStateMapper
import android.firebase.feature.item.remote.model.RemoteItem
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.Observable

class ItemRemoteSourceImpl(
    private val firestore: FirebaseFirestore,
    private val itemMapper: RemoteItemMapper,
    private val itemWithStateMapper: RemoteItemWithStateMapper
) : ItemRemoteSource {

    override fun loadItemsForList(listId: String): Observable<List<ItemWithState>> =
        Observable.create { emitter ->
            val listenerRegistration = firestore
                .collection(COLLECTION_ITEMS)
                .whereEqualTo("listId", listId)
                .limit(50)
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        emitter.onError(e)
                    } else {
                        snapshot?.documentChanges?.map {
                            when (it.type) {
                                DocumentChange.Type.ADDED -> mapToItemWithState(it, STATE.ADDED)
                                DocumentChange.Type.MODIFIED -> mapToItemWithState(it, STATE.MODIFIED)
                                DocumentChange.Type.REMOVED -> mapToItemWithState(it, STATE.REMOVED)
                            }
                        }.let {
                            it?.let { itemsWithState -> emitter.onNext(itemsWithState) }
                        }
                    }
                }
            emitter.setCancellable { listenerRegistration.remove() }
        }

    private fun mapToItemWithState(documentChange: DocumentChange, state: STATE): ItemWithState =
        itemWithStateMapper.map(
            Pair(itemMapper.from(
                documentChange.document.toObject(RemoteItem::class.java)), state))

    override fun saveItem(item: Item) =
        Completable.fromAction {
            val id = firestore
                .collection(COLLECTION_ITEMS)
                .document()
                .id

            val remoteItem = itemMapper.to(item).copy(id = id)

            firestore
                .collection(COLLECTION_ITEMS)
                .document(id)
                .set(remoteItem)
        }

    override fun deleteItem(itemId: String) =
        Completable.fromAction {
            firestore.collection(COLLECTION_ITEMS)
                .document(itemId)
                .delete()
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
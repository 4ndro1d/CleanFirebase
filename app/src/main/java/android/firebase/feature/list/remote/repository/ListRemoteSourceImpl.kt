package android.firebase.feature.list.remote.repository

import android.firebase.feature.item.remote.mapper.RemoteListMapper
import android.firebase.feature.list.data.repository.ListRemoteSource
import android.firebase.feature.list.domain.model.ListWithState
import android.firebase.feature.list.domain.model.MyList
import android.firebase.feature.list.domain.model.STATE
import android.firebase.feature.list.remote.mapper.RemoteListWithStateMapper
import android.firebase.feature.list.remote.model.RemoteList
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import io.reactivex.Completable
import io.reactivex.Observable

class ListRemoteSourceImpl(
    private val firestore: FirebaseFirestore,
    private val listMapper: RemoteListMapper,
    private val listWithStateMapper: RemoteListWithStateMapper
) : ListRemoteSource {

    override fun loadListsForUser(userId: String): Observable<List<ListWithState>> =
        Observable.create { emitter ->
            val listenerRegistration = firestore
                .collection(COLLECTION_LISTS)
                .whereEqualTo("userId", userId)
                .limit(50)
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        emitter.onError(e)
                    } else {
                        snapshot?.documentChanges?.map {
                            when (it.type) {
                                DocumentChange.Type.ADDED -> mapToListWithState(it, STATE.ADDED)
                                DocumentChange.Type.MODIFIED -> mapToListWithState(it, STATE.MODIFIED)
                                DocumentChange.Type.REMOVED -> mapToListWithState(it, STATE.REMOVED)
                            }
                        }.let {
                            it?.let { listsWithState -> emitter.onNext(listsWithState) }
                        }
                    }
                }
            emitter.setCancellable { listenerRegistration.remove() }
        }

    private fun mapToListWithState(documentChange: DocumentChange, state: STATE): ListWithState =
        listWithStateMapper.map(
            Pair(listMapper.from(
                documentChange.document.toObject(RemoteList::class.java)), state))

    override fun saveList(list: MyList): Completable =
        Completable.fromAction {
            val id = firestore
                .collection(COLLECTION_LISTS)
                .document()
                .id

            val remoteItem = listMapper.to(list).copy(id = id)

            firestore
                .collection(COLLECTION_LISTS)
                .document(id)
                .set(remoteItem)
        }

    override fun loadListsSharedWithUser(userId: String): Observable<List<ListWithState>> =
        Observable.create { emitter ->
            val listenerRegistration = firestore
                .collection(COLLECTION_LISTS)
                .whereArrayContains("sharedUserIds", userId)
                .limit(50)
                .addSnapshotListener { snapshot, e ->
                    if (e != null) {
                        emitter.onError(e)
                    } else {
                        snapshot?.documentChanges?.map {
                            when (it.type) {
                                DocumentChange.Type.ADDED -> mapToListWithState(it, STATE.ADDED)
                                DocumentChange.Type.MODIFIED -> mapToListWithState(it, STATE.MODIFIED)
                                DocumentChange.Type.REMOVED -> mapToListWithState(it, STATE.REMOVED)
                            }
                        }.let {
                            it?.let { listsWithState -> emitter.onNext(listsWithState) }
                        }
                    }
                }
            emitter.setCancellable { listenerRegistration.remove() }
        }

    override fun shareListWithUser(listId: String, userId: String): Completable =
        Completable.fromAction {
            firestore.collection(COLLECTION_LISTS)
                .document(listId)
                .set(mapOf(
                    "sharedUserIds" to listOf(userId)
                ), SetOptions.merge())

//            firestore
//                .collection(COLLECTION_SHARED_LISTS)
//                .add(mapOf(
//                    "listId" to listId,
//                    "userId" to userId
//                ))
        }

    private companion object {
        const val COLLECTION_LISTS = "lists"
        const val COLLECTION_SHARED_LISTS = "sharedlists"
    }
}
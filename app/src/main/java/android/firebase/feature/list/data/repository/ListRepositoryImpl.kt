package android.firebase.feature.list.data.repository

import android.firebase.feature.list.domain.model.ListWithState
import android.firebase.feature.list.domain.model.MyList
import android.firebase.feature.list.domain.repository.ListRepository
import io.reactivex.Completable
import io.reactivex.Observable

class ListRepositoryImpl(
    private val remoteSource: ListRemoteSource
) : ListRepository {

    override fun saveList(list: MyList): Completable =
        remoteSource.saveList(list)

    override fun loadListsForUser(userId: String): Observable<List<ListWithState>> =
        remoteSource.loadListsForUser(userId)

    override fun deleteList(listId: String): Completable =
        remoteSource.deleteList(listId)

    override fun loadListsSharedWithUser(userId: String): Observable<List<ListWithState>> =
        remoteSource.loadListsSharedWithUser(userId)

    override fun updateList(list: MyList): Completable =
        remoteSource.updateList(list)

    override fun shareListWithUser(listId: String, userId: String): Completable =
        remoteSource.shareListWithUser(listId, userId)
}
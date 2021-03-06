package android.firebase.feature.list.data.repository

import android.firebase.feature.list.domain.model.ListWithState
import android.firebase.feature.list.domain.model.MyList
import io.reactivex.Completable
import io.reactivex.Observable

interface ListRemoteSource {

    fun saveList(list: MyList): Completable

    fun loadListsForUser(userId: String): Observable<List<ListWithState>>

    fun loadListsSharedWithUser(userId: String): Observable<List<ListWithState>>

    fun shareListWithUser(listId: String, userId: String): Completable

    fun deleteList(listId: String): Completable

    fun updateList(list: MyList): Completable
}
package android.firebase.feature.list.domain.repository

import android.firebase.feature.list.domain.model.ListWithState
import android.firebase.feature.list.domain.model.MyList
import io.reactivex.Completable
import io.reactivex.Observable

interface ListRepository {

    fun saveList(list: MyList): Completable

    fun loadListsForUser(userId: String): Observable<List<ListWithState>>

    fun loadListsSharedWithUser(userId: String): Observable<List<ListWithState>>

    fun shareListWithUser(listId: String, userId: String): Completable
}
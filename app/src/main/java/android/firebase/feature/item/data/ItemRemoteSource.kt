package android.firebase.feature.item.data

import android.firebase.feature.item.domain.model.Item
import io.reactivex.Completable
import io.reactivex.Observable

interface ItemRemoteSource {

    fun loadItemsForCurrentUser(): Observable<List<Item>>

    fun saveItem(item: Item): Completable

    fun updateItem(item: Item): Completable
}

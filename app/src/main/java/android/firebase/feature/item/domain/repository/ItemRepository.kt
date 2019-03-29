package android.firebase.feature.item.domain.repository

import android.firebase.feature.item.domain.model.Item
import android.firebase.feature.item.domain.model.ItemWithState
import io.reactivex.Completable
import io.reactivex.Observable

interface ItemRepository {

    fun loadItemsForList(listId: String): Observable<List<ItemWithState>>

    fun saveItem(item: Item): Completable

    fun updateItem(item: Item): Completable

    fun deleteItem(itemId: String): Completable
}

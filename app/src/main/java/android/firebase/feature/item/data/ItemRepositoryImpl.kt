package android.firebase.feature.item.data

import android.firebase.feature.item.domain.model.Item
import android.firebase.feature.item.domain.repository.ItemRepository

class ItemRepositoryImpl(
    private val remoteSource: ItemRemoteSource
) : ItemRepository {

    override fun loadItemsForUser(userId: String) =
        remoteSource.loadItemsForUser(userId)

    override fun saveItem(item: Item) =
        remoteSource.saveItem(item)

    override fun updateItem(item: Item) =
        remoteSource.updateItem(item)
}
package android.firebase.feature.item.remote.mapper

import android.firebase.feature.item.domain.model.Item
import android.firebase.feature.item.remote.model.RemoteItem
import com.mediasaturn.common.BidirectionalMapper

class RemoteItemMapper : BidirectionalMapper<RemoteItem, Item> {

    override fun from(from: RemoteItem) = with(from) {
        Item(
            id = id,
            userId = userId,
            done = done,
            title = title,
            listId = listId
        )
    }

    override fun to(from: Item): RemoteItem = with(from) {
        RemoteItem(
            id = id,
            userId = userId,
            done = done,
            title = title,
            listId = listId
        )
    }
}
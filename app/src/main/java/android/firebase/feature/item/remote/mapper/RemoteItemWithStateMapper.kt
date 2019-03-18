package android.firebase.feature.item.remote.mapper

import android.firebase.feature.item.domain.model.Item
import android.firebase.feature.item.domain.model.ItemWithState
import android.firebase.feature.item.domain.model.STATE
import com.mediasaturn.common.Mapper

class RemoteItemWithStateMapper : Mapper<Pair<Item, STATE>, ItemWithState> {

    override fun map(from: Pair<Item, STATE>): ItemWithState = with(from) {
        ItemWithState(
            state = second,
            item = first
        )
    }
}
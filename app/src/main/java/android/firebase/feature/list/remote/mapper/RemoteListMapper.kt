package android.firebase.feature.item.remote.mapper

import android.firebase.feature.list.domain.model.MyList
import android.firebase.feature.list.remote.model.RemoteList
import com.mediasaturn.common.BidirectionalMapper

class RemoteListMapper : BidirectionalMapper<RemoteList, MyList> {

    override fun from(from: RemoteList) = with(from) {
        MyList(
            id = id,
            userId = userId,
            title = title,
            sharedUserIds = sharedUserIds
        )
    }

    override fun to(from: MyList): RemoteList = with(from) {
        RemoteList(
            id = id,
            userId = userId,
            title = title,
            sharedUserIds = sharedUserIds
        )
    }
}
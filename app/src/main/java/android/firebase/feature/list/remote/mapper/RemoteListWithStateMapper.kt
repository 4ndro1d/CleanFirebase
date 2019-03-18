package android.firebase.feature.list.remote.mapper

import android.firebase.feature.list.domain.model.ListWithState
import android.firebase.feature.list.domain.model.MyList
import android.firebase.feature.list.domain.model.STATE
import com.mediasaturn.common.Mapper

class RemoteListWithStateMapper : Mapper<Pair<MyList, STATE>, ListWithState> {

    override fun map(from: Pair<MyList, STATE>): ListWithState = with(from) {
        ListWithState(
            state = second,
            list = first
        )
    }
}
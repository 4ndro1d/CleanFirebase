package android.firebase.feature.user.remote.mapper

import android.firebase.feature.user.domain.model.User
import android.firebase.feature.user.remote.model.RemoteUser
import com.mediasaturn.common.BidirectionalMapper

class RemoteUserMapper : BidirectionalMapper<RemoteUser, User> {

    override fun from(from: RemoteUser): User = with(from) {
        User(
            id = id,
            email = email
        )
    }

    override fun to(from: User): RemoteUser = with(from) {
        RemoteUser(
            id = id,
            email = email
        )
    }
}
package android.firebase.feature.list.remote.repository

import android.firebase.feature.list.data.repository.ListRemoteSource
import android.firebase.feature.list.domain.model.MyList
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable

class ListRemoteSourceImpl(
    private val firestore: FirebaseFirestore
) : ListRemoteSource {

    override fun saveList(list: MyList): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
package android.firebase.feature.list.data.repository

import android.firebase.feature.list.domain.model.MyList
import android.firebase.feature.list.domain.repository.ListRepository
import io.reactivex.Completable

class ListRepositoryImpl(
    private val remoteSource: ListRemoteSource
) : ListRepository {

    override fun saveList(list: MyList): Completable =
        remoteSource.saveList(list)
}
package android.firebase.feature.list.data.repository

import android.firebase.feature.list.domain.model.MyList
import io.reactivex.Completable

interface ListRemoteSource {

    fun saveList(list: MyList): Completable
}
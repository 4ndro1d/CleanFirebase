package android.firebase.feature.list.domain.repository

import android.firebase.feature.list.domain.model.MyList
import io.reactivex.Completable

interface ListRepository {

    fun saveList(list: MyList): Completable
}
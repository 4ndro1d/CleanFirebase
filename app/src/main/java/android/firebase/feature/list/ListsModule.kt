package android.firebase.feature.list

import android.firebase.feature.list.data.repository.ListRemoteSource
import android.firebase.feature.list.data.repository.ListRepositoryImpl
import android.firebase.feature.list.domain.repository.ListRepository
import android.firebase.feature.list.presentation.ListsPresenter
import android.firebase.feature.list.remote.repository.ListRemoteSourceImpl
import org.koin.dsl.module.module

val listsModule = module {

    single<ListRemoteSource> { ListRemoteSourceImpl(get()) }
    single<ListRepository> { ListRepositoryImpl(get()) }
    single { ListsPresenter() }

}
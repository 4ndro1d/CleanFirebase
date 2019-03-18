package android.firebase.feature.list

import android.firebase.feature.item.remote.mapper.RemoteListMapper
import android.firebase.feature.list.data.repository.ListRemoteSource
import android.firebase.feature.list.data.repository.ListRepositoryImpl
import android.firebase.feature.list.domain.repository.ListRepository
import android.firebase.feature.list.domain.usecase.LoadListsForUserUseCase
import android.firebase.feature.list.domain.usecase.SaveListUseCase
import android.firebase.feature.list.presentation.ListsPresenter
import android.firebase.feature.list.remote.mapper.RemoteListWithStateMapper
import android.firebase.feature.list.remote.repository.ListRemoteSourceImpl
import org.koin.dsl.module.module

val listsModule = module {

    single { RemoteListWithStateMapper() }
    single { RemoteListMapper() }
    single<ListRemoteSource> { ListRemoteSourceImpl(get(), get(), get()) }
    single<ListRepository> { ListRepositoryImpl(get()) }
    single { LoadListsForUserUseCase(get()) }
    single { SaveListUseCase(get()) }
    single { ListsPresenter(get(), get(), get()) }
}
package android.firebase.feature.item

import android.firebase.feature.item.data.ItemRemoteSource
import android.firebase.feature.item.data.ItemRepositoryImpl
import android.firebase.feature.item.domain.repository.ItemRepository
import android.firebase.feature.item.domain.usecase.LoadItemsForUserUseCase
import android.firebase.feature.item.domain.usecase.SaveItemUseCase
import android.firebase.feature.item.domain.usecase.UpdateItemUseCase
import android.firebase.feature.item.presentation.ItemPresenter
import android.firebase.feature.item.remote.repository.ItemRemoteSourceImpl
import org.koin.dsl.module.module

val itemsModule = module {

    single<ItemRemoteSource> { ItemRemoteSourceImpl(get()) }
    single<ItemRepository> { ItemRepositoryImpl(get()) }
    single { LoadItemsForUserUseCase(get()) }
    single { SaveItemUseCase(get()) }
    single { UpdateItemUseCase(get()) }
    single { ItemPresenter(get(), get(), get(), get()) }
}
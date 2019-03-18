package android.firebase.feature.item

import android.firebase.feature.item.data.ItemRemoteSource
import android.firebase.feature.item.data.ItemRepositoryImpl
import android.firebase.feature.item.domain.repository.ItemRepository
import android.firebase.feature.item.domain.usecase.LoadItemsForUserUseCase
import android.firebase.feature.item.domain.usecase.SaveItemUseCase
import android.firebase.feature.item.domain.usecase.UpdateItemUseCase
import android.firebase.feature.item.presentation.ItemPresenter
import android.firebase.feature.item.remote.mapper.RemoteItemMapper
import android.firebase.feature.item.remote.mapper.RemoteItemWithStateMapper
import android.firebase.feature.item.remote.repository.ItemRemoteSourceImpl
import android.firebase.feature.list.domain.usecase.ShareListWithUserUseCase
import android.firebase.feature.user.domain.usecase.LoadUserByMailUseCase
import android.firebase.feature.user.domain.usecase.ShareListByEmailUseCase
import org.koin.dsl.module.module

val itemsModule = module {

    single { RemoteItemWithStateMapper() }
    single { RemoteItemMapper() }
    single<ItemRemoteSource> { ItemRemoteSourceImpl(get(), get(), get()) }
    single<ItemRepository> { ItemRepositoryImpl(get()) }
    single { LoadItemsForUserUseCase(get()) }
    single { SaveItemUseCase(get()) }
    single { UpdateItemUseCase(get()) }
    single { LoadUserByMailUseCase(get()) }
    single { ShareListByEmailUseCase(get(), get()) }
    single { ShareListWithUserUseCase(get()) }
    single { ItemPresenter(get(), get(), get(), get(), get()) }
}
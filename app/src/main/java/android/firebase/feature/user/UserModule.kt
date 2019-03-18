package android.firebase.feature.user

import android.firebase.feature.user.data.UserRemoteSource
import android.firebase.feature.user.data.UserRepositoryImpl
import android.firebase.feature.user.domain.repository.UserRepository
import android.firebase.feature.user.domain.usecase.SaveUserUseCase
import android.firebase.feature.user.remote.repository.UserRemoteSourceImpl
import org.koin.dsl.module.module

val userModule = module {

    single<UserRemoteSource> { UserRemoteSourceImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single { SaveUserUseCase(get()) }
}
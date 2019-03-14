package android.firebase.feature

import android.firebase.feature.auth.data.UserRemoteSource
import android.firebase.feature.auth.data.UserRepositoryImpl
import android.firebase.feature.auth.domain.repository.UserRepository
import android.firebase.feature.auth.domain.usecase.SaveUserUseCase
import android.firebase.feature.auth.remote.repository.UserRemoteSourceImpl
import org.koin.dsl.module.module

val userModule = module {

    single<UserRemoteSource> { UserRemoteSourceImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single { SaveUserUseCase(get()) }
}
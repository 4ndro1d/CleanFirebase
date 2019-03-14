package android.firebase.feature.auth

import android.firebase.feature.auth.data.AuthRemoteSource
import android.firebase.feature.auth.data.AuthRepositoryImpl
import android.firebase.feature.auth.domain.repository.AuthRepository
import android.firebase.feature.auth.domain.usecase.LoadAuthenticatedUserUseCase
import android.firebase.feature.auth.presentation.AuthPresenter
import android.firebase.feature.auth.remote.repository.AuthRemoteSourceImpl
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module.module

val authModule = module {

    single<FirebaseAuth> { FirebaseAuth.getInstance() }
    single<AuthRemoteSource> { AuthRemoteSourceImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single { LoadAuthenticatedUserUseCase(get()) }
    single { AuthPresenter(get(), get()) }
}
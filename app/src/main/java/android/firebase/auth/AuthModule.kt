package android.firebase.auth

import android.firebase.auth.data.AuthRemoteSource
import android.firebase.auth.data.AuthRepositoryImpl
import android.firebase.auth.domain.AuthRepository
import android.firebase.auth.domain.IsUserAuthenticatedUseCase
import android.firebase.auth.presentation.AuthPresenter
import android.firebase.auth.remote.AuthRemoteSourceImpl
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module.module

val authModule = module {

    single<FirebaseAuth> { FirebaseAuth.getInstance() }
    single<AuthRemoteSource> { AuthRemoteSourceImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single { IsUserAuthenticatedUseCase(get()) }
    single { AuthPresenter(get()) }
}
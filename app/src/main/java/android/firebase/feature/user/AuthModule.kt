package android.firebase.feature.user

import android.firebase.feature.user.data.AuthRemoteSource
import android.firebase.feature.user.data.AuthRepositoryImpl
import android.firebase.feature.user.domain.repository.AuthRepository
import android.firebase.feature.user.domain.usecase.LoadAuthenticatedUserUseCase
import android.firebase.feature.user.presentation.AuthPresenter
import android.firebase.feature.user.remote.repository.AuthRemoteSourceImpl
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module.module

val authModule = module {

    single<FirebaseAuth> { FirebaseAuth.getInstance() }
    single<AuthRemoteSource> { AuthRemoteSourceImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single { LoadAuthenticatedUserUseCase(get()) }
    single { AuthPresenter(get(), get()) }
}
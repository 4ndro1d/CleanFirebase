package android.firebase

import android.firebase.authentication.AuthenticationPresenter
import android.firebase.presentation.MainActivityPresenter
import org.koin.dsl.module.module

val androidModule = module {

    single { MainActivityPresenter() }
    single { AuthenticationPresenter() }
}
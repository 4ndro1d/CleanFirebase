package android.firebase.main

import android.app.Application
import android.firebase.common.firestoreModule
import android.firebase.feature.auth.authModule
import android.firebase.feature.lists.listsModule
import android.firebase.feature.userModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin(this, listOf(
            firestoreModule,
            authModule,
            userModule,
            listsModule
        ))
    }
}
package android.firebase

import android.app.Application
import android.firebase.auth.authModule
import android.firebase.firestore.fireStoreModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin(this, listOf(
            authModule,
            fireStoreModule
        ))
    }
}
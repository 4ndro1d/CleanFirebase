package android.firebase

import android.app.Application
import android.firebase.auth.authModule
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(
            androidModule,
            authModule
        ))
    }
}
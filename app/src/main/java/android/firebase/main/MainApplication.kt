package android.firebase.main

import android.app.Application
import android.firebase.common.firestoreModule
import android.firebase.feature.user.authModule
import android.firebase.feature.user.userModule
import android.firebase.feature.item.itemsModule
import android.firebase.feature.list.listsModule
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
            listsModule,
            itemsModule
        ))
    }
}
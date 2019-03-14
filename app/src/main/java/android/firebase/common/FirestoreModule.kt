package android.firebase.common

import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module.module

val firestoreModule = module {

    single { FirebaseFirestore.getInstance() }
}
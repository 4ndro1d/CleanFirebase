package android.firebase.firestore

import android.firebase.firestore.data.TodoRemoteSource
import android.firebase.firestore.data.TodoRepositoryImpl
import android.firebase.firestore.domain.AddTodoUseCase
import android.firebase.firestore.domain.TodoRepository
import android.firebase.firestore.presentation.TodoPresenter
import android.firebase.firestore.remote.TodoRemoteSourceImpl
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module.module

val fireStoreModule = module {

    single { FirebaseFirestore.getInstance() }
    single<TodoRemoteSource> { TodoRemoteSourceImpl(get()) }
    single<TodoRepository> { TodoRepositoryImpl(get()) }
    single { AddTodoUseCase(get()) }
    single { TodoPresenter(get()) }
}
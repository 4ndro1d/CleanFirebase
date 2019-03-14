package android.firebase.feature.lists

import android.firebase.feature.lists.data.TodoRemoteSource
import android.firebase.feature.lists.data.TodoRepositoryImpl
import android.firebase.feature.lists.domain.repository.TodoRepository
import android.firebase.feature.lists.domain.usecase.AddTodoUseCase
import android.firebase.feature.lists.domain.usecase.UpdateTodoUseCase
import android.firebase.feature.lists.presentation.TodoPresenter
import android.firebase.feature.lists.remote.repository.TodoRemoteSourceImpl
import org.koin.dsl.module.module

val listsModule = module {

    single<TodoRemoteSource> { TodoRemoteSourceImpl(get()) }
    single<TodoRepository> { TodoRepositoryImpl(get()) }
    single { AddTodoUseCase(get()) }
    single { UpdateTodoUseCase(get()) }
    single { TodoPresenter(get(), get(), get()) }
}
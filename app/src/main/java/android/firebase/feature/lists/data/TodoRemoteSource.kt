package android.firebase.feature.lists.data

import android.firebase.feature.lists.domain.model.Todo
import io.reactivex.Completable

interface TodoRemoteSource {

    fun loadTodos(): List<Todo>

    fun addTodo(todo: Todo): Completable

    fun updateTodo(todo: Todo): Completable
}

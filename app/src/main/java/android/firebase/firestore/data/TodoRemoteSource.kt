package android.firebase.firestore.data

import android.firebase.firestore.domain.model.Todo
import io.reactivex.Completable

interface TodoRemoteSource {

    fun loadTodos(): List<Todo>

    fun addTodo(todo: Todo): Completable

    fun updateTodo(todo: Todo): Completable
}

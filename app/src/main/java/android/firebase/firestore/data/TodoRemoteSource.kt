package android.firebase.firestore.data

import android.firebase.firestore.domain.model.Todo
import io.reactivex.Single

interface TodoRemoteSource {

    fun loadTodos(): List<Todo>

    fun addTodo(todo: Todo): Single<Todo>

    fun updateTodo(todo: Todo)
}

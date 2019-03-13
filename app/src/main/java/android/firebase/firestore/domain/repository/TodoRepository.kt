package android.firebase.firestore.domain.repository

import android.firebase.firestore.domain.model.Todo
import io.reactivex.Single

interface TodoRepository {

    fun loadTodos(): List<Todo>

    fun addTodo(todo: Todo): Single<Todo>

    fun updateTodo(todo: Todo)
}
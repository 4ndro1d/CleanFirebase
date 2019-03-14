package android.firebase.firestore.domain.repository

import android.firebase.firestore.domain.model.Todo
import io.reactivex.Completable

interface TodoRepository {

    fun loadTodos(): List<Todo>

    fun addTodo(todo: Todo): Completable

    fun updateTodo(todo: Todo): Completable
}
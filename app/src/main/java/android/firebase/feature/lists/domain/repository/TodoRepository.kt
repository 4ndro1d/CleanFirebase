package android.firebase.feature.lists.domain.repository

import android.firebase.feature.lists.domain.model.Todo
import io.reactivex.Completable

interface TodoRepository {

    fun loadTodos(): List<Todo>

    fun addTodo(todo: Todo): Completable

    fun updateTodo(todo: Todo): Completable
}
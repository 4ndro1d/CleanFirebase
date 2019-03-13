package android.firebase.firestore.domain

import android.firebase.firestore.domain.model.Todo

interface TodoRepository {

    fun loadTodos(): List<Todo>

    fun addTodo(title: String, done: Boolean)

    fun updateTodo(id: String, title: String, done: Boolean)
}
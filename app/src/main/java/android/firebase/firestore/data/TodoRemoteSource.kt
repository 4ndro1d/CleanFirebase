package android.firebase.firestore.data

import android.firebase.firestore.domain.model.Todo

interface TodoRemoteSource {

    fun loadTodos(): List<Todo>

    fun addTodo(title: String, done: Boolean)

    fun updateTodo(id: String, title: String, done: Boolean)
}

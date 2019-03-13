package android.firebase.firestore.data

import android.firebase.firestore.domain.TodoRepository
import android.firebase.firestore.domain.model.Todo

class TodoRepositoryImpl(
    private val remoteSource: TodoRemoteSource
) : TodoRepository {

    override fun loadTodos(): List<Todo> =
        remoteSource.loadTodos()

    override fun addTodo(title: String, done: Boolean) {
        remoteSource.addTodo(title, done)
    }

    override fun updateTodo(title: String, done: Boolean) {
        remoteSource.updateTodo(title, done)
    }
}
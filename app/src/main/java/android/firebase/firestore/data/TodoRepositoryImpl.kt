package android.firebase.firestore.data

import android.firebase.firestore.domain.model.Todo
import android.firebase.firestore.domain.repository.TodoRepository
import io.reactivex.Single

class TodoRepositoryImpl(
    private val remoteSource: TodoRemoteSource
) : TodoRepository {

    override fun loadTodos(): List<Todo> =
        remoteSource.loadTodos()

    override fun addTodo(todo: Todo): Single<Todo> =
        remoteSource.addTodo(todo)

    override fun updateTodo(todo: Todo) {
        remoteSource.updateTodo(todo)
    }
}
package android.firebase.feature.lists.data

import android.firebase.feature.lists.domain.model.Todo
import android.firebase.feature.lists.domain.repository.TodoRepository
import io.reactivex.Completable

class TodoRepositoryImpl(
    private val remoteSource: TodoRemoteSource
) : TodoRepository {

    override fun loadTodos(): List<Todo> =
        remoteSource.loadTodos()

    override fun addTodo(todo: Todo): Completable =
        remoteSource.addTodo(todo)

    override fun updateTodo(todo: Todo) =
        remoteSource.updateTodo(todo)
}
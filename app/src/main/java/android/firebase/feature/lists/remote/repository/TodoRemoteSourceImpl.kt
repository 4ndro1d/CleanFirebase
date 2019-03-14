package android.firebase.feature.lists.remote.repository

import android.firebase.feature.lists.data.TodoRemoteSource
import android.firebase.feature.lists.domain.model.Todo
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable

class TodoRemoteSourceImpl(
    private val firestore: FirebaseFirestore
) : TodoRemoteSource {

    override fun loadTodos(): List<Todo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addTodo(todo: Todo) =
        Completable.fromAction {
            val id = firestore
                .collection(COLLECTION_TODOS)
                .document()
                .id

            val newTodo = todo.copy(id = id)

            firestore
                .collection(COLLECTION_TODOS)
                .document(id)
                .set(newTodo)
        }

    override fun updateTodo(todo: Todo) =
        Completable.fromAction {
            firestore.collection(COLLECTION_TODOS)
                .document(todo.id)
                .set(todo)
        }

    companion object {
        const val COLLECTION_TODOS = "todos"
    }
}
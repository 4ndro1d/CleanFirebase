package android.firebase.firestore.remote

import android.firebase.firestore.data.TodoRemoteSource
import android.firebase.firestore.domain.model.Todo
import com.androidhuman.rxfirebase2.firestore.RxFirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestore

class TodoRemoteSourceImpl(
    private val firestore: FirebaseFirestore
) : TodoRemoteSource {

    override fun loadTodos(): List<Todo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addTodo(todo: Todo) =
        RxFirebaseFirestore.add(firestore.collection(COLLECTION_TODOS), todo)
            .map {
                Todo(it.id, todo.title, todo.done)
            }

    override fun updateTodo(todo: Todo) {
        firestore.collection(COLLECTION_TODOS)
            .document(todo.id)
            .set(todo)
    }

    companion object {
        const val COLLECTION_TODOS = "todos"
    }
}
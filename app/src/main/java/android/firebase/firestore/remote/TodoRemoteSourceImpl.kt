package android.firebase.firestore.remote

import android.firebase.firestore.data.TodoRemoteSource
import android.firebase.firestore.domain.model.Todo
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

class TodoRemoteSourceImpl(
    private val firestore: FirebaseFirestore
) : TodoRemoteSource {

    override fun loadTodos(): List<Todo> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addTodo(title: String, done: Boolean) {
        val todo = HashMap<String, Any>().apply {
            set("title", title)
            set("done", done)
        }

        firestore.collection(COLLECTION_TODOS)
            .add(todo)
            .addOnSuccessListener { documentReference ->
                Timber.d("DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Timber.w(e, "Error adding document")
            }
    }

    override fun updateTodo(id: String, title: String, done: Boolean) {
        val todo = HashMap<String, Any>().apply {
            set("title", title)
            set("done", done)
        }

        firestore.collection(COLLECTION_TODOS)
            .document(id)
            .set(todo)
    }

    companion object {
        const val COLLECTION_TODOS = "todos"
    }
}
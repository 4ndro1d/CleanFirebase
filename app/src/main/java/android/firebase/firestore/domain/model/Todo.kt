package android.firebase.firestore.domain.model

data class Todo(
    val id: String = "",
    val title: String = "",
    val done: Boolean = false
)
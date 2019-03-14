package android.firebase.feature.lists.domain.model

data class Todo(
    val id: String = "",
    val title: String = "",
    val done: Boolean = false,
    val userId: String = ""
)
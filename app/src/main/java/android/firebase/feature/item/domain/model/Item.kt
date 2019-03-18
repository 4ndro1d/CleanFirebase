package android.firebase.feature.item.domain.model

data class Item(
    val id: String = "",
    val listId: String = "",
    val title: String = "",
    val done: Boolean = false,
    val userId: String = ""
)
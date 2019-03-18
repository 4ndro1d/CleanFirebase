package android.firebase.feature.item.remote.model

data class RemoteItem(
    val id: String = "",
    val title: String = "",
    val done: Boolean = false,
    val userId: String = "",
    val sharedUserIds: List<String> = emptyList()
)
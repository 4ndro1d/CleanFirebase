package android.firebase.feature.list.remote.model

data class RemoteList(
    val id: String = "",
    val title: String = "",
    val userId: String = "",
    val sharedUserIds: List<String> = emptyList()
)
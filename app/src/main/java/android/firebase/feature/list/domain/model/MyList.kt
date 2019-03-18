package android.firebase.feature.list.domain.model

data class MyList(
    val id: String = "",
    val title: String = "",
    val userId: String = "",
    val sharedUserIds: List<String> = emptyList()
)
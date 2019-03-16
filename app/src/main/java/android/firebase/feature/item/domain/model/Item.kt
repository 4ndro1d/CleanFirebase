package android.firebase.feature.item.domain.model

data class Item(
    val id: String = "",
    val title: String = "",
    val done: Boolean = false,
    val userId: String = "",
    val sharedUserIds: List<String> = listOf("bojdfs")
)
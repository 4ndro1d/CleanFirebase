package android.firebase.feature.item.domain.model

data class ItemWithState(
    val state: STATE,
    val item: Item
)

enum class STATE {
    ADDED,
    REMOVED,
    MODIFIED
}
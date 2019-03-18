package android.firebase.feature.list.domain.model

data class ListWithState(
    val state: STATE,
    val list: MyList
)

enum class STATE {
    ADDED,
    REMOVED,
    MODIFIED
}
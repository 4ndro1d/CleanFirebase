package android.firebase.feature.list.presentation

import android.firebase.feature.list.domain.model.MyList

interface ListsView {

    fun navigateToItemsForList(listId: String, title: String)

    fun showInputDialog()

    fun listRemoved(list: MyList)

    fun listAdded(item: MyList)

    fun listModified(item: MyList)

    fun showNotAuthenticated()

    fun showError(message: String?)
}

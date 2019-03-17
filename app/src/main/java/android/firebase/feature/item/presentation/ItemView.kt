package android.firebase.feature.item.presentation

import android.firebase.common.view.BaseView
import android.firebase.feature.item.domain.model.Item

interface ItemView : BaseView {

    fun showInputDialog()

    fun showItems(items: List<Item>)

    fun itemRemoved(item: Item)

    fun itemAdded(item: Item)

    fun itemModified(item: Item)

    fun showNotAuthenticated()

    fun showError(message: String?)
}
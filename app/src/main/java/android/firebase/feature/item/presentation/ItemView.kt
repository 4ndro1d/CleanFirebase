package android.firebase.feature.item.presentation

import android.firebase.common.view.BaseView
import android.firebase.feature.item.domain.model.Item

interface ItemView : BaseView {

    fun itemRemoved(item: Item)

    fun itemAdded(item: Item)

    fun itemModified(item: Item)

    fun showNotAuthenticated()

    fun showError(message: String?)

    fun showEmailInputDialog()

    fun showEditDialog(item: Item)

    fun showInviteError()

    fun showInviteSuccess()

    fun showMissingInput()

    fun clearInput()
}
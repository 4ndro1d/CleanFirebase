package android.firebase.feature.item.presentation

import android.firebase.common.view.BaseView
import android.firebase.feature.item.domain.model.Item

interface ItemView : BaseView {

    fun showInputDialog()

    fun initAdapter(items: List<Item>)
}
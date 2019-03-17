package android.firebase.feature.list.presentation

import android.firebase.common.presentation.BasePresenter
import android.firebase.feature.list.domain.model.MyList

class ListsPresenter(

) : BasePresenter<ListsView> {

    lateinit var view: ListsView

    override fun start(view: ListsView) {
        this.view = view
    }

    fun onListClicked(list: MyList) {
        view.navigateToItemsForList(list.id)
    }
}
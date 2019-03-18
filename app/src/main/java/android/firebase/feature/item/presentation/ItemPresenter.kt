package android.firebase.feature.item.presentation

import android.firebase.common.presentation.BasePresenter
import android.firebase.extensions.observeOnMain
import android.firebase.extensions.subscribeOnIo
import android.firebase.feature.auth.domain.usecase.LoadAuthenticatedUserUseCase
import android.firebase.feature.item.domain.model.Item
import android.firebase.feature.item.domain.model.STATE
import android.firebase.feature.item.domain.usecase.LoadItemsForUserUseCase
import android.firebase.feature.item.domain.usecase.SaveItemUseCase
import android.firebase.feature.item.domain.usecase.UpdateItemUseCase
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class ItemPresenter(
    private val loadItemsForListUseCase: LoadItemsForUserUseCase,
    private val saveItemUseCase: SaveItemUseCase,
    private val updateItemUseCase: UpdateItemUseCase,
    private val loadAuthenticatedUserUseCase: LoadAuthenticatedUserUseCase
) : BasePresenter<ItemView> {

    private lateinit var view: ItemView
    private lateinit var listId: String

    fun setListId(listId: String) {
        this.listId = listId
    }

    override fun start(view: ItemView) {
        this.view = view

        listId.let {
            disposables += loadItemsForListUseCase.execute(LoadItemsForUserUseCase.Params(it))
                .subscribeOnIo()
                .observeOnMain()
                .subscribeBy(
                    onNext = { itemsWithState ->
                        itemsWithState.map { withState ->
                            when (withState.state) {
                                STATE.REMOVED -> view.itemRemoved(withState.item)
                                STATE.ADDED -> view.itemAdded(withState.item)
                                STATE.MODIFIED -> view.itemModified(withState.item)
                            }
                        }
                    },
                    onError = { e ->
                        Timber.e(e)
                        view.showError(e.message)
                    }
                )
        }
    }

    fun addItemButtonClicked() {
        view.showInputDialog()
    }

    fun addItem(title: String) {
        loadAuthenticatedUserUseCase.execute()?.uid?.let {
            disposables +=
                saveItemUseCase.execute(SaveItemUseCase.Params(
                    Item(title = title,
                        listId = listId,
                        userId = it)))
                    .subscribeOnIo()
                    .observeOnMain()
                    .subscribeBy(
                        onError = Timber::e
                    )
        } ?: view.showNotAuthenticated()
    }

    fun onItemCheckedChange(item: Item) {
        updateItem(item)
    }

    private fun updateItem(item: Item) {
        disposables += updateItemUseCase.execute(UpdateItemUseCase.Params(item))
            .subscribeOnIo()
            .observeOnMain()
            .subscribeBy(
                onError = Timber::e
            )
    }
}
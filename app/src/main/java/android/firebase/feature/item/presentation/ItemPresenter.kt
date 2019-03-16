package android.firebase.feature.item.presentation

import android.firebase.common.presentation.BasePresenter
import android.firebase.extensions.observeOnMain
import android.firebase.extensions.subscribeOnIo
import android.firebase.feature.auth.domain.usecase.LoadAuthenticatedUserUseCase
import android.firebase.feature.item.domain.model.Item
import android.firebase.feature.item.domain.usecase.LoadItemsUseCase
import android.firebase.feature.item.domain.usecase.SaveItemUseCase
import android.firebase.feature.item.domain.usecase.UpdateItemUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ItemPresenter(
    private val loadItemsUseCase: LoadItemsUseCase,
    private val saveItemUseCase: SaveItemUseCase,
    private val updateItemUseCase: UpdateItemUseCase,
    private val loadAuthenticatedUserUseCase: LoadAuthenticatedUserUseCase
) : BasePresenter<ItemView> {

    private lateinit var view: ItemView

    override fun start(view: ItemView) {
        this.view = view

        disposables += loadItemsUseCase.execute()
            .subscribeOnIo()
            .observeOnMain()
            .subscribeBy(
                onNext = { view.initAdapter(it) },
                onError = Timber::e
            )
    }

    fun addTodoButtonClicked() {
        view.showInputDialog()
    }

    fun addItem(title: String) {
        disposables +=
            saveItemUseCase.execute(SaveItemUseCase.Params(
                Item("",
                    title,
                    false,
                    loadAuthenticatedUserUseCase.execute()?.uid ?: "guest")))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun onItemCheckedChange(item: Item) {
        updateItem(item)
    }

    private fun updateItem(item: Item) {
        updateItemUseCase.execute(UpdateItemUseCase.Params(item))
    }
}
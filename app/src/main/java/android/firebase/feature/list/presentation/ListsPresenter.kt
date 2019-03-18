package android.firebase.feature.list.presentation

import android.firebase.common.presentation.BasePresenter
import android.firebase.extensions.observeOnMain
import android.firebase.extensions.subscribeOnIo
import android.firebase.feature.auth.domain.usecase.LoadAuthenticatedUserUseCase
import android.firebase.feature.list.domain.model.MyList
import android.firebase.feature.list.domain.model.STATE
import android.firebase.feature.list.domain.usecase.LoadListsForUserUseCase
import android.firebase.feature.list.domain.usecase.SaveListUseCase
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class ListsPresenter(
    private val loadListsForUserUseCase: LoadListsForUserUseCase,
    private val loadAuthenticatedUserUseCase: LoadAuthenticatedUserUseCase,
    private val saveListUseCase: SaveListUseCase
) : BasePresenter<ListsView> {

    lateinit var view: ListsView

    override fun start(view: ListsView) {
        this.view = view

        loadAuthenticatedUserUseCase.execute()?.uid?.let {
            disposables += loadListsForUserUseCase.execute(LoadListsForUserUseCase.Params(it))
                .subscribeOnIo()
                .observeOnMain()
                .subscribeBy(
                    onNext = { listsWithState ->
                        listsWithState.map { withState ->
                            when (withState.state) {
                                STATE.REMOVED -> view.listRemoved(withState.list)
                                STATE.ADDED -> view.listAdded(withState.list)
                                STATE.MODIFIED -> view.listModified(withState.list)
                            }
                        }
                    },
                    onError = { e ->
                        Timber.e(e)
                        view.showError(e.message)
                    }
                )
        } ?: view.showNotAuthenticated()
    }

    fun addList(title: String) {
        disposables +=
            saveListUseCase.execute(SaveListUseCase.Params(
                MyList(title = title,
                    userId = loadAuthenticatedUserUseCase.execute()?.uid ?: "guest")))
                .subscribeOnIo()
                .observeOnMain()
                .subscribeBy(
                    onError = Timber::e
                )
    }

    fun onListClicked(list: MyList) {
        view.navigateToItemsForList(list.id, list.title)
    }

    fun addListButtonClicked() {
        view.showInputDialog()
    }
}
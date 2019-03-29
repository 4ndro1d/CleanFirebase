package android.firebase.feature.list.presentation

import android.firebase.common.presentation.BasePresenter
import android.firebase.extensions.observeOnMain
import android.firebase.extensions.subscribeOnIo
import android.firebase.feature.list.domain.model.MyList
import android.firebase.feature.list.domain.model.STATE
import android.firebase.feature.list.domain.usecase.DeleteListUseCase
import android.firebase.feature.list.domain.usecase.LoadListsForUserUseCase
import android.firebase.feature.list.domain.usecase.LoadSharedListsForUserUseCase
import android.firebase.feature.list.domain.usecase.SaveListUseCase
import android.firebase.feature.user.domain.usecase.LoadAuthenticatedUserUseCase
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

class ListsPresenter(
    private val loadListsForUserUseCase: LoadListsForUserUseCase,
    private val loadSharedListsForUserUseCase: LoadSharedListsForUserUseCase,
    private val deleteListUseCase: DeleteListUseCase,
    private val loadAuthenticatedUserUseCase: LoadAuthenticatedUserUseCase,
    private val saveListUseCase: SaveListUseCase
) : BasePresenter<ListsView>() {

    lateinit var view: ListsView

    override fun start(view: ListsView) {
        this.view = view

        //TODO extract to usecase
        loadAuthenticatedUserUseCase.execute()?.uid?.let {
            disposables += loadListsForUserUseCase.execute(LoadListsForUserUseCase.Params(it))
                .mergeWith(loadSharedListsForUserUseCase.execute(LoadSharedListsForUserUseCase.Params(it)))
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
        loadAuthenticatedUserUseCase.execute()?.uid?.let {
            disposables +=
                saveListUseCase.execute(SaveListUseCase.Params(MyList(title = title, userId = it)))
                    .subscribeOnIo()
                    .observeOnMain()
                    .subscribeBy(
                        onError = { e -> view.showError(e.message) }
                    )
        } ?: view.showNotAuthenticated()
    }

    fun onListClicked(list: MyList) {
        view.navigateToItemsForList(list.id, list.title)
    }

    fun addListButtonClicked() {
        view.showInputDialog()
    }

    fun listSwiped(myList: MyList) {
        loadAuthenticatedUserUseCase.execute()?.uid?.let {
            if (myList.userId == it) {
                view.showDeleteListConfirmation(myList)
            } else {
                view.showMissingPermission()
            }
        } ?: view.showNotAuthenticated()
    }

    fun deleteList(myList: MyList) {
        disposables += deleteListUseCase.execute(DeleteListUseCase.Params(myList.id))
            .subscribeOnIo()
            .observeOnMain()
            .subscribeBy(
                onError = { view.showError(it.message) }
            )
    }
}
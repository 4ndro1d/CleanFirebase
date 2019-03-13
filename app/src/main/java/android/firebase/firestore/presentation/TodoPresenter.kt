package android.firebase.firestore.presentation

import android.firebase.common.presentation.BasePresenter
import android.firebase.firestore.domain.model.Todo
import android.firebase.firestore.domain.usecase.AddTodoUseCase
import android.firebase.firestore.domain.usecase.UpdateTodoUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class TodoPresenter(
    private val addTodoUseCase: AddTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase
) : BasePresenter<TodoView> {

    private lateinit var view: TodoView

    override fun start(view: TodoView) {
        this.view = view
    }

    fun addTodoButtonClicked() {
        view.showInputDialog()
    }

    fun addTodo(title: String) {
        disposables += addTodoUseCase.execute(AddTodoUseCase.Params(Todo("", title, false)))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { Timber.d("$it") },
                onError = Timber::e
            )
    }

    fun todoChecked(todo: Todo) {
        updateTodoUseCase.execute(UpdateTodoUseCase.Params(todo))
    }
}
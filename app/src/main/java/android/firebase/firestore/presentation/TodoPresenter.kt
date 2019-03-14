package android.firebase.firestore.presentation

import android.firebase.auth.domain.LoadCurrentUserUseCase
import android.firebase.common.presentation.BasePresenter
import android.firebase.firestore.domain.model.Todo
import android.firebase.firestore.domain.usecase.AddTodoUseCase
import android.firebase.firestore.domain.usecase.UpdateTodoUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class TodoPresenter(
    private val addTodoUseCase: AddTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val loadCurrentUserUseCase: LoadCurrentUserUseCase
) : BasePresenter<TodoView> {

    private lateinit var view: TodoView

    override fun start(view: TodoView) {
        this.view = view
    }

    fun addTodoButtonClicked() {
        view.showInputDialog()
    }

    fun addTodo(title: String) {
        disposables +=
            addTodoUseCase.execute(AddTodoUseCase.Params(
                Todo("",
                    title,
                    false,
                    loadCurrentUserUseCase.execute()?.uid ?: "guest")))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun updateTodo(todo: Todo) {
        updateTodoUseCase.execute(UpdateTodoUseCase.Params(todo))
    }
}
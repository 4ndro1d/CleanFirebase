package android.firebase.feature.lists.presentation

import android.firebase.feature.auth.domain.usecase.LoadAuthenticatedUserUseCase
import android.firebase.common.presentation.BasePresenter
import android.firebase.feature.lists.domain.model.Todo
import android.firebase.feature.lists.domain.usecase.AddTodoUseCase
import android.firebase.feature.lists.domain.usecase.UpdateTodoUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class TodoPresenter(
    private val addTodoUseCase: AddTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val loadAuthenticatedUserUseCase: LoadAuthenticatedUserUseCase
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
                    loadAuthenticatedUserUseCase.execute()?.uid ?: "guest")))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun updateTodo(todo: Todo) {
        updateTodoUseCase.execute(UpdateTodoUseCase.Params(todo))
    }
}
package android.firebase.firestore.presentation

import android.firebase.common.presentation.BasePresenter
import android.firebase.firestore.domain.AddTodoUseCase

class TodoPresenter(
    private val addTodoUseCase: AddTodoUseCase
) : BasePresenter<TodoView> {

    private lateinit var view: TodoView

    override fun start(view: TodoView) {
        this.view = view
    }

    fun addTodo() {
        addTodoUseCase.execute(AddTodoUseCase.Params("Some title", false))
    }
}
package android.firebase.firestore.presentation

import android.firebase.common.presentation.BasePresenter
import android.firebase.firestore.domain.AddTodoUseCase
import android.firebase.firestore.domain.UpdateTodoUseCase
import android.firebase.firestore.domain.model.Todo

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
        addTodoUseCase.execute(AddTodoUseCase.Params(title, false))
    }

    fun todoChecked(todo: Todo) {
        updateTodoUseCase.execute(UpdateTodoUseCase.Params(todo.id, todo.title, todo.done))
    }
}
package android.firebase.firestore.domain.usecase

import android.firebase.firestore.domain.model.Todo
import android.firebase.firestore.domain.repository.TodoRepository
import android.firebase.firestore.domain.usecase.UpdateTodoUseCase.Params
import com.mediasaturn.common.domain.UseCase

class UpdateTodoUseCase(
    private val repository: TodoRepository
) : UseCase<Params, Unit> {

    override fun execute(param: Params) {
        repository.updateTodo(param.todo)
    }

    data class Params(
        val todo: Todo
    )
}
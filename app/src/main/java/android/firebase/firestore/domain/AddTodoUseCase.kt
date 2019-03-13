package android.firebase.firestore.domain

import com.mediasaturn.common.domain.UseCase

class AddTodoUseCase(
    private val repository: TodoRepository
) : UseCase<AddTodoUseCase.Params, Unit> {

    override fun execute(param: Params) {
        repository.addTodo(param.title, param.done)
    }

    data class Params(
        val title: String,
        val done: Boolean
    )
}
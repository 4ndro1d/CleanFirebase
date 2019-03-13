package android.firebase.firestore.domain

import android.firebase.firestore.domain.UpdateTodoUseCase.Params
import com.mediasaturn.common.domain.UseCase

class UpdateTodoUseCase(
    private val repository: TodoRepository
) : UseCase<Params, Unit> {

    override fun execute(param: Params) {
        repository.updateTodo(param.id, param.title, param.done)
    }

    data class Params(
        val id: String,
        val title: String,
        val done: Boolean
    )
}
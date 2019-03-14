package android.firebase.feature.lists.domain.usecase

import android.firebase.feature.lists.domain.model.Todo
import android.firebase.feature.lists.domain.repository.TodoRepository
import com.mediasaturn.common.domain.UseCase
import io.reactivex.Completable

class AddTodoUseCase(
    private val repository: TodoRepository
) : UseCase<AddTodoUseCase.Params, Completable> {

    override fun execute(param: Params) =
        repository.addTodo(param.todo)

    data class Params(
        val todo: Todo
    )
}
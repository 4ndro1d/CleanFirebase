package android.firebase.feature.lists.domain.usecase

import android.firebase.feature.lists.domain.repository.TodoRepository
import com.mediasaturn.common.domain.NonArgUseCase

class LoadTodosUseCase(
    private val repository: TodoRepository
) : NonArgUseCase<Unit> {

    override fun execute() {
        repository.loadTodos()
    }
}
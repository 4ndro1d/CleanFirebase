package android.firebase.firestore.domain.usecase

import android.firebase.firestore.domain.repository.TodoRepository
import com.mediasaturn.common.domain.NonArgUseCase

class LoadTodosUseCase(
    private val repository: TodoRepository
) : NonArgUseCase<Unit> {

    override fun execute() {
        repository.loadTodos()
    }
}
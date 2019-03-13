package android.firebase.firestore.domain

import com.mediasaturn.common.domain.NonArgUseCase

class LoadTodosUseCase(
    private val repository: TodoRepository
) : NonArgUseCase<Unit> {

    override fun execute() {
        repository.loadTodos()
    }
}
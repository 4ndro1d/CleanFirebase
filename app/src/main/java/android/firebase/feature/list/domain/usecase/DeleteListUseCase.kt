package android.firebase.feature.list.domain.usecase

import android.firebase.feature.list.domain.repository.ListRepository
import com.mediasaturn.common.domain.UseCase
import io.reactivex.Completable

class DeleteListUseCase(
    private val repository: ListRepository
) : UseCase<DeleteListUseCase.Params, Completable> {

    override fun execute(param: Params): Completable =
        repository.deleteList(param.listId)

    data class Params(
        val listId: String
    )
}

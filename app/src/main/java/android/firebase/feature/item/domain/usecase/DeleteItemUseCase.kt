package android.firebase.feature.item.domain.usecase

import android.firebase.feature.item.domain.repository.ItemRepository
import com.mediasaturn.common.domain.UseCase
import io.reactivex.Completable

class DeleteItemUseCase(
    private val repository: ItemRepository
) : UseCase<DeleteItemUseCase.Params, Completable> {

    override fun execute(param: Params): Completable =
        repository.deleteItem(param.itemId)

    data class Params(
        val itemId: String
    )
}
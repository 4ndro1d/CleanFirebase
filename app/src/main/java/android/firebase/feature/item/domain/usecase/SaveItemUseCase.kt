package android.firebase.feature.item.domain.usecase

import android.firebase.feature.item.domain.model.Item
import android.firebase.feature.item.domain.repository.ItemRepository
import com.mediasaturn.common.domain.UseCase
import io.reactivex.Completable

class SaveItemUseCase(
    private val repository: ItemRepository
) : UseCase<SaveItemUseCase.Params, Completable> {

    override fun execute(param: Params) =
        repository.saveItem(param.item)

    data class Params(
        val item: Item
    )
}
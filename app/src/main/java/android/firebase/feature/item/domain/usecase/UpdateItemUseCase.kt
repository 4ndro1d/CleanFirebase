package android.firebase.feature.item.domain.usecase

import android.firebase.feature.item.domain.model.Item
import android.firebase.feature.item.domain.repository.ItemRepository
import android.firebase.feature.item.domain.usecase.UpdateItemUseCase.Params
import com.mediasaturn.common.domain.UseCase

class UpdateItemUseCase(
    private val repository: ItemRepository
) : UseCase<Params, Unit> {

    override fun execute(param: Params) {
        repository.updateItem(param.item)
    }

    data class Params(
        val item: Item
    )
}
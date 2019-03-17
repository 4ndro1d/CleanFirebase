package android.firebase.feature.item.domain.usecase

import android.firebase.feature.item.domain.model.ItemWithState
import android.firebase.feature.item.domain.repository.ItemRepository
import com.mediasaturn.common.domain.NonArgUseCase
import io.reactivex.Observable

class LoadItemsUseCase(
    private val repository: ItemRepository
) : NonArgUseCase<Observable<List<ItemWithState>>> {

    override fun execute() =
        repository.loadItemsForCurrentUser()
}
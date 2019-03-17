package android.firebase.feature.item.domain.usecase

import android.firebase.feature.item.domain.model.ItemWithState
import android.firebase.feature.item.domain.repository.ItemRepository
import android.firebase.feature.item.domain.usecase.LoadItemsForUserUseCase.Params
import com.mediasaturn.common.domain.UseCase
import io.reactivex.Observable

class LoadItemsForUserUseCase(
    private val repository: ItemRepository
) : UseCase<Params, Observable<List<ItemWithState>>> {

    override fun execute(param: Params): Observable<List<ItemWithState>> =
        repository.loadItemsForUser(param.userId)

    data class Params(
        val userId: String
    )
}
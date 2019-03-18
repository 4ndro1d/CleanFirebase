package android.firebase.feature.list.domain.usecase

import android.firebase.feature.list.domain.model.ListWithState
import android.firebase.feature.list.domain.repository.ListRepository
import com.mediasaturn.common.domain.UseCase
import io.reactivex.Observable

class LoadListsForUserUseCase(
    private val repository: ListRepository
) : UseCase<LoadListsForUserUseCase.Params, Observable<List<ListWithState>>> {

    override fun execute(param: Params): Observable<List<ListWithState>> =
        repository.loadListsForUser(param.userId)

    data class Params(
        val userId: String
    )
}

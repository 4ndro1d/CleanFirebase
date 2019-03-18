package android.firebase.feature.list.domain.usecase

import android.firebase.feature.list.domain.model.ListWithState
import android.firebase.feature.list.domain.repository.ListRepository
import com.mediasaturn.common.domain.UseCase
import io.reactivex.Observable

class LoadSharedListsForUserUseCase(
    private val repository: ListRepository
) : UseCase<LoadSharedListsForUserUseCase.Params, Observable<List<ListWithState>>> {

    override fun execute(param: Params): Observable<List<ListWithState>> =
        repository.loadListsSharedWithUser(param.userId)

    data class Params(
        val userId: String
    )
}

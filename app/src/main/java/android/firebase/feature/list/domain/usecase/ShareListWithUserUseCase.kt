package android.firebase.feature.list.domain.usecase

import android.firebase.feature.list.domain.repository.ListRepository
import com.mediasaturn.common.domain.UseCase
import io.reactivex.Completable

class ShareListWithUserUseCase(
    private val repository: ListRepository
) : UseCase<ShareListWithUserUseCase.Params, Completable> {

    override fun execute(param: Params): Completable =
        repository.shareListWithUser(param.listId, param.userId)

    data class Params(
        val listId: String,
        val userId: String
    )
}
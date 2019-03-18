package android.firebase.feature.user.domain.usecase

import android.firebase.feature.list.domain.usecase.ShareListWithUserUseCase
import com.mediasaturn.common.domain.UseCase
import io.reactivex.Completable

class ShareListByEmailUseCase(
    private val loadUserByMailUseCase: LoadUserByMailUseCase,
    private val shareListWithUserUseCase: ShareListWithUserUseCase
) : UseCase<ShareListByEmailUseCase.Params, Completable> {

    override fun execute(param: Params): Completable =
        loadUserByMailUseCase.execute(LoadUserByMailUseCase.Params(param.email))
            .flatMapCompletable {
                shareListWithUserUseCase.execute(
                    ShareListWithUserUseCase.Params(listId = param.listId, userId = it.id))
            }

    data class Params(
        val email: String,
        val listId: String
    )
}

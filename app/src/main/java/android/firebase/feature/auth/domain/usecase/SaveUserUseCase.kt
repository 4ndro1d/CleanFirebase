package android.firebase.feature.auth.domain.usecase

import android.firebase.feature.auth.domain.repository.UserRepository
import com.mediasaturn.common.domain.UseCase
import io.reactivex.Completable

class SaveUserUseCase(
    private val repository: UserRepository
) : UseCase<SaveUserUseCase.Params, Completable> {

    override fun execute(param: Params) =
        repository.saveUser(param.id, param.email)

    data class Params(
        val id: String?,
        val email: String
    )
}
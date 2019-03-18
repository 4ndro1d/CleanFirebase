package android.firebase.feature.user.domain.usecase

import android.firebase.feature.user.domain.model.User
import android.firebase.feature.user.domain.repository.UserRepository
import com.mediasaturn.common.domain.UseCase
import io.reactivex.Single

class LoadUserByMailUseCase(
    private val repository: UserRepository
) : UseCase<LoadUserByMailUseCase.Params, Single<User>> {

    override fun execute(param: Params) =
        repository.loadUserByEmail(param.email)

    data class Params(
        val email: String
    )
}
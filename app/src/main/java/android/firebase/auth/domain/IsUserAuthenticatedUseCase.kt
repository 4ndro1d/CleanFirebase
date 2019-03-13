package android.firebase.auth.domain

import com.mediasaturn.common.domain.NonArgUseCase

class IsUserAuthenticatedUseCase(
    private val repository: AuthRepository
) : NonArgUseCase<Boolean> {

    override fun execute(): Boolean =
        repository.isUserAuthenticated()
}
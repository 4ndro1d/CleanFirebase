package android.firebase.auth.domain

import com.google.firebase.auth.FirebaseUser
import com.mediasaturn.common.domain.NonArgUseCase

class LoadCurrentUserUseCase(
    private val repository: AuthRepository
) : NonArgUseCase<FirebaseUser?> {

    override fun execute() =
        repository.loadCurrentUser()
}
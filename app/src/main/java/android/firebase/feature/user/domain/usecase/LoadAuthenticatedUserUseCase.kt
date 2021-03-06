package android.firebase.feature.user.domain.usecase

import android.firebase.feature.user.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import com.mediasaturn.common.domain.NonArgUseCase

class LoadAuthenticatedUserUseCase(
    private val repository: AuthRepository
) : NonArgUseCase<FirebaseUser?> {

    override fun execute() =
        repository.loadAuthenticatedUser()
}
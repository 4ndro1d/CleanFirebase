package android.firebase.auth.domain

interface AuthRepository {

    fun isUserAuthenticated(): Boolean
}
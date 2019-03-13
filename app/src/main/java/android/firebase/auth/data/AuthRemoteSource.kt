package android.firebase.auth.data

interface AuthRemoteSource {

    fun isUserAuthenticated(): Boolean
}
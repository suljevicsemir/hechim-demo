package com.semirsuljevic.foundation.internal.authentication

import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.auth
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore

import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.authentication.model.HechimUser
import com.semirsuljevic.foundation.api.common.HechimError
import com.semirsuljevic.foundation.api.common.HechimResource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HechimAuthenticationFirebase @Inject constructor(): HechimAuthentication{
    override fun isAuthenticated() = Firebase.auth.currentUser != null

    override suspend fun checkEmail(email: String): Boolean{
        val task: Task<QuerySnapshot> = Firebase.firestore
            .collection("users")
            .whereEqualTo(
                "email", email
            )
            .get()
        val querySnapshot: QuerySnapshot = task.await()
        return !querySnapshot.isEmpty
    }

    override suspend fun login(email: String, password: String): HechimResource<HechimUser>{
        try {
            val task: Task<AuthResult> = Firebase.auth.signInWithEmailAndPassword(email, password)
            val authResult: AuthResult = task.await()
            return if(authResult.user == null) {
                HechimResource.Error(
                    HechimError(
                        message = "Login failed",
                        buttonTitle = "Try again"
                    )
                )
            }
            else {
                HechimResource.Success(
                    data = HechimUser(
                        id = authResult.user?.uid ?: "",
                        email = authResult.user?.uid ?: ""
                    )
                )
            }
        }
        catch (e: Exception) {
            return HechimResource.Error(
                error = HechimError(
                    message = "Login failed",
                    buttonTitle = "Try again"
                )
            )
        }
    }

    private suspend fun storeUser(
        email: String,
        id: String
    ) {
        val task: Task<Void> = Firebase.firestore
            .collection("users")
            .document(id)
            .set(mapOf(
                    "email" to email,
                )
            )
        task.await()

    }

    override suspend fun register(email: String, password: String): HechimResource<HechimUser> {
        try {
            val task: Task<AuthResult> = Firebase.auth.createUserWithEmailAndPassword(email, password)
            val authResult : AuthResult = task.await()
            return if(authResult.user == null)
                HechimResource.Error(
                    HechimError(
                        message = "Login failed",
                        buttonTitle = "Try again"
                    )
                )
            else {
                login(email, password)
                storeUser(
                    email = email,
                    id = authResult.user?.uid ?: ""
                )
                return HechimResource.Success(
                    data = HechimUser(
                        id = authResult.user?.uid ?: "",
                        email = authResult.user?.uid ?: ""
                    )
                )
            }
        }
        catch (e: Exception) {
            return HechimResource.Error(
                HechimError(
                    message = "Login failed",
                    buttonTitle = "Try again"
                )
            )
        }

    }
}

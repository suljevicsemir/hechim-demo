package com.semirsuljevic.foundation.internal.authentication

import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore

import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HechimAuthenticationImpl @Inject constructor(

): HechimAuthentication{
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

    override suspend fun login(email: String, password: String) {
        val task: Task<AuthResult> = Firebase.auth.signInWithEmailAndPassword(email, password)
        val authResult: AuthResult = task.await()
    }

    override suspend fun register(email: String, password: String) {
        val task: Task<AuthResult> = Firebase.auth.createUserWithEmailAndPassword(email, password)
        val authResult : AuthResult = task.await()
        println("auth result is: $authResult")

    }
}

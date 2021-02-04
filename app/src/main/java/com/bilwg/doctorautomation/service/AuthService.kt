package com.bilwg.doctorautomation.service

import com.bilwg.doctorautomation.model.User
import com.bilwg.doctorautomation.callbacks.LoginCallback
import com.bilwg.doctorautomation.constants.Collections
import com.bilwg.doctorautomation.constants.ErrorCode
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception

class AuthService(private val loginCallback: LoginCallback) {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    val isLoggedIn = firebaseAuth.currentUser != null

    fun loginWithEmail(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            val user = it.user
            if (user != null) {
                getUserData(user.uid) { resultUser ->
                    if (resultUser == null) {
                        loginCallback.onLoginFailed("Cannot get user from server. Error code : ${ErrorCode.NOT_GET_USER}")
                        return@getUserData
                    }
                    loginCallback.onLoginSuccess(resultUser)
                }
            } else {
                loginCallback.onLoginFailed("Something went wrong. Error code : ${ErrorCode.AUTH_FAILED_ON_SUCCESS}")
            }
        }.addOnFailureListener {
            loginCallback.onLoginFailed(
                it.localizedMessage ?: "Something went wrong. Error code : ${ErrorCode.AUTH_FAILED}"
            )
        }
    }

    fun loginWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnSuccessListener {
            val user = it.user
            if (user != null) {
                getUserData(user.uid) { resultUser ->
                    if (resultUser != null) {
                        loginCallback.onLoginSuccess(resultUser)
                    } else {
                        loginCallback.onLoginSuccessWithoutDB(user)
                    }
                }
            } else {
                loginCallback.onLoginFailed("Something went wrong. Error code : ${ErrorCode.AUTH_FAILED_ON_SUCCESS}")
            }

        }.addOnFailureListener {
            loginCallback.onLoginFailed(
                it.localizedMessage ?: "Something went wrong. Error code : ${ErrorCode.AUTH_FAILED}"
            )
        }
    }

    fun loginWithFacebook() {}

    fun addUserData(user: User) {
        db.collection(Collections.USER).document(user.id).set(user)
            .addOnSuccessListener {
                loginCallback.onLoginSuccess(user)
            }.addOnFailureListener {
                loginCallback.onLoginFailed(
                    it.localizedMessage
                        ?: "Something went wrong. Error code : ${ErrorCode.AUTH_FAILED}"
                )
            }
    }

    private fun getUserData(uid: String, callback: (user: User?) -> Unit) {
        db.collection(Collections.USER).document(uid).get().addOnSuccessListener {
            try {
                val user = User.from(it)
                callback(user)
            } catch (e: Exception) {
                callback(null)
            }
        }.addOnFailureListener {
            loginCallback.onLoginFailed(
                it.localizedMessage
                    ?: "Something went wrong. Error code : ${ErrorCode.CANNOT_GET_USER_FROM_DB}"
            )
        }
    }

}
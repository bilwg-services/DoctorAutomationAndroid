package com.bilwg.doctorautomation.callbacks

import com.bilwg.doctorautomation.model.User
import com.google.firebase.auth.FirebaseUser

interface LoginCallback {
    fun onLoginSuccess(user: User)
    fun onLoginFailed(error:String)
    fun onLoginSuccessWithoutDB(user:FirebaseUser)
}
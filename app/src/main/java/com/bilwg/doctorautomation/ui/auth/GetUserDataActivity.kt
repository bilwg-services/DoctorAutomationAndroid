package com.bilwg.doctorautomation.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bilwg.doctorautomation.R
import com.google.firebase.auth.FirebaseUser

class GetUserDataActivity : AppCompatActivity() {

    private lateinit var firebaseUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_user_data)
        firebaseUser = intent.getParcelableExtra("firebaseUser") as FirebaseUser
    }
}

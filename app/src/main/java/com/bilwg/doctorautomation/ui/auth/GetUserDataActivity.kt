package com.bilwg.doctorautomation.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bilwg.doctorautomation.R
import com.google.firebase.auth.FirebaseUser

class GetUserDataActivity : AppCompatActivity() {

    private var firebaseUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_user_data)
        firebaseUser = intent.getParcelableExtra<FirebaseUser?>("firebaseUser")
    }
}

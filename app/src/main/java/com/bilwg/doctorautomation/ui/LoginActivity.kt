package com.bilwg.doctorautomation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.bilwg.doctorautomation.MainActivity
import com.bilwg.doctorautomation.R
import com.bilwg.doctorautomation.callbacks.LoginCallback
import com.bilwg.doctorautomation.databinding.ActivityLoginBinding
import com.bilwg.doctorautomation.model.User
import com.bilwg.doctorautomation.service.AuthService
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity(), LoginCallback {

    companion object {
        private const val RC_SIGN_IN = 69
        private const val TAG = "LoginActivity"
    }

    private lateinit var binding: ActivityLoginBinding
    private val authService = AuthService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)


        binding.googleBtn.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }
    }

    override fun onLoginSuccess(user: User) {
        Toast.makeText(this, "Welcome ${user.name}", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        this.finish()
    }

    override fun onLoginFailed(error: String) {
        AlertDialog.Builder(this).setTitle("Error").setPositiveButton("Ok") { _, _ -> }
            .setMessage(error).show()
    }

    override fun onLoginSuccessWithoutDB(user: FirebaseUser) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("firebaseUser", user)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                authService.loginWithGoogle(account!!)
            } catch (e: ApiException) {
                Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
                Log.e(TAG, "Google sign in failed", e)
            }
        }
    }
}

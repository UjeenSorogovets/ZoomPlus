package com.example.zoomplus

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class LoginActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()


    }
    val TAG = "MyTagActivity"

    fun loginFun(view: View){
        val email = findViewById<TextView>(R.id.emailLoginText).text.toString()
        val password = findViewById<TextView>(R.id.passwordLoginText).text.toString()
        Log.d(TAG, "Login!")
        //FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            //.addOnCompleteListener()
    }

    fun toRegistrationFun(view: View) {
        //val intent = Intent(this, MainActivity::class.java)
        //startActivity(intent)
        finish()
    }

}
package com.example.evoid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main)

        loginMain.setOnClickListener {
            val emailId = emailIdMain.text.toString()
            val password = passwordMain.text.toString()

            if (validateForm(emailId, password)) //check if all the details are entered
            {
                signUser(emailId, password, auth)

            }


        }

        newRegisterMain.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivity(intent)

        }

        forgotPasswordMain.setOnClickListener {
            val intent = Intent(this, forgotPassword::class.java)
            startActivity(intent)


        }


    }

    private fun validateForm(emailid: String, password: String): Boolean
    {
        if (emailid.isEmpty() || password.isEmpty())
        {
            enterAllDetailsMain.text = "*Please enter all the details*"
            return false
        }
        else return true

    }

    private fun signUser(emailid: String, password: String, auth: FirebaseAuth) {
        auth.signInWithEmailAndPassword(emailid, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, "signed in successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)
                } else {
                    Log.w("failure", "signInWithEmail:failure", task.exception)
                    Toast.makeText(this, "Sign in failed.",
                        Toast.LENGTH_SHORT).show()

                }

            }



    }



}
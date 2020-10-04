package com.example.evoid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_main)



        login_main.setOnClickListener {
            val emailid = email_id_main.text.toString()
            val password = password_main.text.toString()

            if (validateForm(emailid, password)) //check if all the details are entered
            {
                signUser(emailid, password, auth)
            }


        }

        new_register_main.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivity(intent)

        }

        forgot_password_main.setOnClickListener {
            val intent = Intent(this, forgotPassword::class.java)
            startActivity(intent)


        }


    }

    private fun validateForm(emailid: String, password: String): Boolean
    {
        if (emailid.isEmpty() || password.isEmpty())
        {
            enter_all_details_main.text = "*Please enter all the details"
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
                } else {
                    Log.w("failure", "signInWithEmail:failure", task.exception)
                    Toast.makeText(this, "Sign in failed.",
                        Toast.LENGTH_SHORT).show()

                }

            }



    }


}
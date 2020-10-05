package com.example.evoid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class register : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        val currentUser = auth.currentUser
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_register)


        backToLoginRegister.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }



        registerNowRegister.setOnClickListener {
            val firstName = firstNameRegister.text.toString()
            val lastName = lastNameRegister.text.toString()
            val mobile = phoneNumberRegister.text.toString()
            val emailId = emailIdRegister.text.toString()
            val password = passwordRegister.text.toString()

            if (validateForm(firstName, lastName, mobile, emailId, password)){

                registerUser(firstName, lastName, mobile, emailId, password, auth)
            }




        }


    }

    private fun validateForm(firstName: String, lastName: String, mobile: String, emailid: String, password: String): Boolean
    {
        if (firstName.isEmpty() || lastName.isEmpty() || mobile.isEmpty() || emailid.isEmpty() || password.isEmpty())
        {
            enterAllDetailsRegister.text = "*Please enter all the details"
            return false
        }
        else return true

    }

    private fun registerUser(firstName: String, lastName: String, mobile: String, emailid: String, password: String, auth: FirebaseAuth) {

        auth.createUserWithEmailAndPassword(emailid, password).addOnCompleteListener(this){task->
            if (task.isSuccessful) {
                val user = auth.currentUser
                val intent = Intent(this, registered::class.java)
                startActivity(intent)
                finish()

                // Sign in success, update UI with the signed-in user's information

            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
            }


        }

        }

    }



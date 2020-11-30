package com.example.evoid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register_user.*
import kotlinx.android.synthetic.main.activity_start_page.*

class RegisterUser : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setContentView(R.layout.activity_register_user)
        cardViewRegisterUser.setBackgroundResource(R.drawable.cardcorners)

        registerRegisterUser.setOnClickListener {
            val firstName = firstNameRegisterUser.text.toString()
            val lastName = lastNameRegisterUser.text.toString()
            val mobile = phoneNumberRegisterUser.text.toString()
            val emailId = emailIdRegisterUser.text.toString()
            val password = passwordRegisterUser.text.toString()

            if (validateForm(firstName, lastName, mobile, emailId, password)){


                registerUser(firstName, lastName, mobile, emailId, password, auth)
            }




        }
    }


    private fun validateForm(firstName: String, lastName: String, mobile: String, emailid: String, password: String): Boolean
    {
        if (firstName.isEmpty())
        {
            firstNameRegisterUser.setError("It should not be empty")
            return false
        }
        if (lastName.isEmpty())
        {
            lastNameRegisterUser.setError("It should not be empty")
            return false
        }
        if (emailid.isEmpty())
        {
            emailIdRegisterUser.setError("Email should not be empty")
            return false
        }
        if (mobile.isEmpty())
        {
            phoneNumberRegisterUser.setError("Mobile Number should not be empty")
            return false
        }
        if (password.isEmpty())
        {
            passwordRegisterUser.setError("Password should not be empty")
            return false
        }
        else
            return true

    }

    private fun registerUser(firstName: String, lastName: String, mobile: String, emailId: String, password: String,
                             auth: FirebaseAuth) {

        auth.createUserWithEmailAndPassword(emailId, password).addOnCompleteListener(this){task->
            if (task.isSuccessful) {
                val currentUser = auth.currentUser
                val user = com.example.evoid.User(currentUser!!.uid, firstName, lastName, mobile.toLong(), emailId)
                firestoreClass().registerUser(this, user)
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
                finish()

                // Sign in success, update UI with the signed-in user's information

            } else {
                Toast.makeText(this, "Registration failed. Please provide valid user details", Toast.LENGTH_SHORT).show()
            }


        }

    }

    override fun onBackPressed() {
        val intent = Intent(this, start_page::class.java)
        startActivity(intent)
        finish()
    }
}
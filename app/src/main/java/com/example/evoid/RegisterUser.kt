package com.example.evoid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register_user.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class RegisterUser : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        val MobilePattern = "[0-9]{10}"
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
    private fun isValid(s: String): Boolean {
        return s.length == 10
    }

    private fun isLetters(string: String):Boolean{
        for (c in string) {
            if (c !in 'A'..'Z' && c !in 'a'..'z') {
                print("i")
                return true
            }

        }
        return false
    }




    private fun validateForm(
        firstName: String,
        lastName: String,
        mobile: String,
        emailid: String,
        password: String,
    ): Boolean
    {
        if (firstName.isEmpty())
        {
            firstNameRegisterUser.error = "It should not be empty"
            return false
        }
        if (lastName.isEmpty())
        {
            lastNameRegisterUser.error = "It should not be empty"
            return false
        }
        if (emailid.isEmpty())
        {
            emailIdRegisterUser.error = "Email should not be empty"
            return false
        }
        if (mobile.isEmpty())
        {
            phoneNumberRegisterUser.error = "Mobile Number should not be empty"
            return false
        }
        if (password.isEmpty())
        {
            passwordRegisterUser.error = "Password should not be empty"
            return false
        }
        if(!isValid(mobile))
        { phoneNumberRegisterUser.error = "Please enter a valid phone number"
            return false
        }
        if(!isLetters(mobile))
        { phoneNumberRegisterUser.error = "Please enter a valid phone number"
            return false
        }
        else
            return true
    }


    private fun registerUser(
        firstName: String, lastName: String, mobile: String, emailId: String, password: String,
        auth: FirebaseAuth,
    ) {

        auth.createUserWithEmailAndPassword(emailId, password).addOnCompleteListener(this){ task->
            if (task.isSuccessful) {
                val currentUser = auth.currentUser
                val user = com.example.evoid.User(currentUser!!.uid,
                    firstName,
                    lastName,
                    mobile.toLong(),
                    emailId)
                firestoreClass().registerUser(this, user)
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
                finish()

                // Sign in success, update UI with the signed-in user's information

            } else {
                if (!task.isSuccessful) {
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        Toast.makeText(this,
                            "Password should have more than 6 characters",
                            Toast.LENGTH_SHORT).show()
                        passwordRegisterUser.requestFocus()
                    } catch (e: FirebaseAuthException) {
                        Toast.makeText(this, "Please enter a valid Email ID", Toast.LENGTH_SHORT).show()
                        emailIdRegisterUser.requestFocus()
                    }


                }
                //Toast.makeText(this, "Registration failed. Please provide valid user details", Toast.LENGTH_SHORT).show()
            }


        }

    }

    override fun onBackPressed() {
        val intent = Intent(this, start_page::class.java)
        startActivity(intent)
        finish()
    }
}
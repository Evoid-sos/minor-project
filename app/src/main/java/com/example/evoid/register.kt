package com.example.evoid

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
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


        back_to_login_register.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }



        register_now_register.setOnClickListener {
            val first_name = first_name_register.text.toString()
            val last_name = last_name_register.text.toString()
            val mobile = phone_number_register.text.toString()
            val emailid = email_id_register.text.toString()
            val password = password_register.text.toString()

            if (validateForm(first_name, last_name, mobile, emailid, password)){

                registerUser(first_name, last_name, mobile, emailid, password, auth)
            }




        }


    }

    private fun validateForm(firstName: String, lastName: String, mobile: String, emailid: String, password: String): Boolean
    {
        if (firstName.isEmpty() || lastName.isEmpty() || mobile.isEmpty() || emailid.isEmpty() || password.isEmpty())
        {
            enter_all_details_register.text = "*Please enter all the details"
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



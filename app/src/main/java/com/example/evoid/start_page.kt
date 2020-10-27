package com.example.evoid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_start_page.*

class start_page : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        var b = 0
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_start_page)

        if (auth.currentUser != null)
        {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }


        showHideStart.setOnClickListener() {
            if (b == 0)
            {
                passwordStart.transformationMethod = HideReturnsTransformationMethod.getInstance();
                b = 1;
                showHideStart.visibility = View.INVISIBLE
                showHide2Start.visibility = View.VISIBLE

            }

        }

        showHide2Start.setOnClickListener(){
            if (b==1)
            {
                passwordStart.transformationMethod = PasswordTransformationMethod.getInstance();
                b=0
                showHide2Start.visibility = View.INVISIBLE
                showHideStart.visibility = View.VISIBLE
            }
        }





        loginStart.setOnClickListener {
            val emailId = emailIdStart.text.toString()
            val password = passwordStart.text.toString()

            if (validateForm(emailId, password)) //check if all the details are entered
            {
                signUser(emailId, password, auth)

            }


        }

        registerStart.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivity(intent)

        }

        forgotPasswordStart.setOnClickListener {
            val intent = Intent(this, forgotPassword::class.java)
            startActivity(intent)


        }


    }

    private fun validateForm(emailid: String, password: String): Boolean
    {
        if (emailid.isEmpty())
        {
            emailIdStart.setError("Email should not be empty")
            return false
        }
        if (password.isEmpty())
        {
            passwordStart.setError("Password should not be empty")
            return false
        }
        else return true

    }

    private fun signUser(emailid: String, password: String, auth: FirebaseAuth) {
        auth.signInWithEmailAndPassword(emailid, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this, "Signed In Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity2::class.java)
                    startActivity(intent)

                } else {
                    Log.w("failure", "signInWithEmail:failure", task.exception)
                    Toast.makeText(this, "Incorrect Email/Password", Toast.LENGTH_SHORT).show()
                    //Toast.makeText(this, "E-mail Id/Password Incorrect",Toast.LENGTH_SHORT).show()

                }

            }



    }





}
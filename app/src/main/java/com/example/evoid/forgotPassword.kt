package com.example.evoid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register_user.*
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timerTask

class forgotPassword : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setContentView(R.layout.activity_forgot_password)
        cardViewForgotPassword.setBackgroundResource(R.drawable.cardcorners)
        auth = Firebase.auth


        sendMail.setOnClickListener {
            val emailId = emailIdForgotPassword.text.toString()
            if (validateForm(emailId))
            {
                resetPassword(emailId,auth)
                //sentMail.text = "A recovery mail is sent to you, see it for more deatils."
            }
        }

    }

    private fun validateForm(emailId: String): Boolean {
        if (emailId.isEmpty())
        {
            emailIdForgotPassword.setError("It should not be empty")
            return false
        }
        else return true

    }

    private fun resetPassword(emailId: String, auth: FirebaseAuth) {
        auth.sendPasswordResetEmail(emailId).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "A recovery mail is sent to you, see it for more deatils", Toast.LENGTH_SHORT).show()
                val handler = Handler()
                handler.postDelayed(
                    {val intent = Intent(this, start_page::class.java)
                    startActivity(intent)
                    finish()
                    },
                    5000)


            } else {
                Toast.makeText(this, "Kindly check your Email Id", Toast.LENGTH_SHORT).show()


        }



        }


    }

    override fun onBackPressed() {
        val intent = Intent(this, start_page::class.java)
        startActivity(intent)
        finish()
    }
}
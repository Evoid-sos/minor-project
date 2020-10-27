package com.example.evoid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat.format
import android.view.View
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_registered.*
import java.lang.String.format
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class registered : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private val mFireStore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        setContentView(R.layout.activity_registered)
        auth = Firebase.auth

        val currentUser = auth.currentUser
        val timestamp = currentUser!!.metadata!!.creationTimestamp
        val d = getDate(timestamp)


        fun displayRegisterUser()
        {
            mFireStore.collection(constants.USERS)
                .document(currentUser!!.uid)
                .get()
                .addOnSuccessListener {document->
                    fullNameRegistered.text = document.get("firstName").toString() + " " + document.get("lastName").toString()
                    phoneRegistered.text = document.get("phoneNumber").toString()
                    emailRegistered.text = document.get("emailId").toString()
                    dateRegistered.text = d.toString()




                }
        }

        displayRegisterUser()






        backToLoginRegistered.setOnClickListener {
            val intent = Intent(this, start_page::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getDate(timestamp: Long):String {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeInMillis = timestamp * 1000L
        val netDte = Date(timestamp)
        val sdf = SimpleDateFormat("dd/MM/yy")
        val date = sdf.format(netDte)
        return date


    }
}
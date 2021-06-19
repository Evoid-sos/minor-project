package com.example.evoid

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_cybersecurity.*

class cybersecurity : AppCompatActivity() {
    private val mFireStore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cybersecurity)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        getActionBarLang(supportActionBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        reportLink.movementMethod= LinkMovementMethod.getInstance()
        firestoreClass().loadCyberProtectionPage(textView6, reportLink)
        cybercrime.setOnClickListener()
        {
            textView6.transformationMethod = HideReturnsTransformationMethod.getInstance();
            textView6.visibility = View.INVISIBLE
            firestoreClass().loadCyberProtectionGuidelines(showGuidelines)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun getActionBarLang(supportActionBar: ActionBar?) {
        var loggedInUser: User
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener { document ->
                loggedInUser = document.toObject(User::class.java)!!
                if (loggedInUser.lang == "en") {

                    supportActionBar?.title = Html.fromHtml("<font color='#fffffff'><small>CYBER CRIME PROTECTION GUIDELINES </font></small>")

                }
                if (loggedInUser.lang == "hi") {

                    supportActionBar?.title = Html.fromHtml("<font color='#fffffff'><small>साइबर अपराध सुरक्षा दिशानिर्देश </font></small>")

                }
            }

    }

    fun getCurrentUserId():String
    {
        val uid = Firebase.auth.currentUser!!.uid
        return uid
    }
}


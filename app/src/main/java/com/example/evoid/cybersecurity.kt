package com.example.evoid

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.view.View
import kotlinx.android.synthetic.main.activity_cybersecurity.*

class cybersecurity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cybersecurity)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = Html.fromHtml("<font color='#fffffff'><small>CYBER CRIME PROTECTION GUIDELINES </font></small>")
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
}


package com.example.evoid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.method.HideReturnsTransformationMethod
import android.text.method.LinkMovementMethod
import android.view.View
import kotlinx.android.synthetic.main.activity_cybersecurity.*
import kotlinx.android.synthetic.main.activity_cybersecurity.*
import kotlinx.android.synthetic.main.activity_start_page.*
import kotlinx.android.synthetic.main.fragment_emergency.*

class cybersecurity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cybersecurity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = Html.fromHtml("<font color='#fffffff'><small>CYBER CRIME PROTECTION GUIDELINES </font></small>")
        reportLink.movementMethod= LinkMovementMethod.getInstance()
        cybercrime.setOnClickListener()
        {
            textView6.transformationMethod = HideReturnsTransformationMethod.getInstance();
            textView6.visibility = View.INVISIBLE
            showGuidelines.text= resources.getString(R.string.cyber_security_guidelines_details)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}


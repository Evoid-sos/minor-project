package com.example.evoid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import kotlinx.android.synthetic.main.activity_cybersecurity.*
import kotlinx.android.synthetic.main.activity_cybersecurity.*

class cybersecurity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cybersecurity)
        title="Cyber Crimes Protection Guidelines"
        reportLink.movementMethod= LinkMovementMethod.getInstance()
        cybercrime.setOnClickListener()
        {

            showGuidelines.text= resources.getString(R.string.cyber_security_guidelines_details)
        }
    }
}

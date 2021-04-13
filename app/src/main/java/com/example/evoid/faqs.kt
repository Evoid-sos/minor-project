package com.example.evoid

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import kotlinx.android.synthetic.main.activity_faqs.*

class faqs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faqs)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = Html.fromHtml("<font color='#fffffff'>FREQUENTLY ASKED QUESTIONS </font>")
        val one="<b><i>\u2022 Add up to 5 trusted contacts from the 'Emergency Contacts' tab<br><br>" +
                "<b><i>\u2022 Use the 'Panic Button' to send location to trusted contacts through SMS<br><br>" +
                "<b><i>\u2022 When the Panic Button is pressed, you would be prompted to click picture from back and front camera. These images would be sent to your trusted contacts as link via SMS<br><br>"
        quesOne.text=Html.fromHtml(one)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
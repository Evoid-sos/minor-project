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
        supportActionBar!!.title = Html.fromHtml("<font color='#fffffff'>INSTRUCTIONS </font>")
        val one="<b><i>\u2022 Add up to 5 trusted contacts from the 'Emergency Contacts' tab<br><br>" +
                "<b><i>\u2022 Use the 'Panic Button' to send location to trusted contacts through SMS<br><br>" +
                "<b><i>\u2022 When the Panic Button is pressed, you would be prompted to click picture from back and front camera. These images would be sent to your trusted contacts as link via SMS<br><br>" +
                "<b><i>\u2022 You can check your current location using the 'My Location' option<br><br>" +
                "<b><i>\u2022 'Start Alarm' option can be used to raise an alarm to alert people around you<br><br>" +
                "<b><i>\u2022 You can search for nearby Police Stations, Hospitals, Fire Station, Old Age Shelters and Pharmacies using the 'Find Help Near Me' menu option<br><br>" +
                "<b><i>\u2022 You can update your username and phone number by tapping on the image displayed on top of the menu"
        quesOne.text=Html.fromHtml(one)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
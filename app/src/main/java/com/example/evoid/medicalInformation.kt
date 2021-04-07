package com.example.evoid

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_medical_information.*
import kotlinx.android.synthetic.main.activity_my_profile.*

class medicalInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_information)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = Html.fromHtml("<font color='#fffffff'> <small>MY MEDICAL INFORMATION </font></small>")
        cardViewMedicalInformation.setBackgroundResource(R.drawable.cardcorners)
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
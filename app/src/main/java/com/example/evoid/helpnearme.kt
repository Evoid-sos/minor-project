package com.example.evoid

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html


class helpnearme : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_helpnearme)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title =
            Html.fromHtml("<font color='#fffffff'> <small>FIND HELP NEAR ME  </font></small>")
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}




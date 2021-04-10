package com.example.evoid

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import kotlinx.android.synthetic.main.activity_cybersecurity.*
import kotlinx.android.synthetic.main.activity_helpnearme.*


class helpnearme : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_helpnearme)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = Html.fromHtml("<font color='#fffffff'> <small>FIND HELP NEAR ME  </font></small>")
        policehelp.setOnClickListener {
            maplayout.visibility= View.VISIBLE
        }
        hospitalhelp.setOnClickListener {
            maplayout.visibility=View.VISIBLE
        }
        firehelp.setOnClickListener {
            maplayout.visibility=View.VISIBLE
        }
        oldagehelp.setOnClickListener {
            maplayout.visibility=View.VISIBLE
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}




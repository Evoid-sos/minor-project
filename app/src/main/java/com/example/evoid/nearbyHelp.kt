package com.example.evoid

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import kotlinx.android.synthetic.main.activity_nearby_help.*

class nearbyHelp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_help)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = Html.fromHtml("<font color='#fffffff'>FIND HELP NEAR ME </font>")
        policeStations.setOnClickListener {
            val uri: Uri =
                Uri.parse("https://www.google.com/maps/search/police+stations+near+me") // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        hospitals.setOnClickListener {
            val uri: Uri =
                Uri.parse("https://www.google.com/maps/search/hospitals+near+me") // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        fireStations.setOnClickListener {
            val uri: Uri =
                Uri.parse("https://www.google.com/maps/search/fire+stations+near+me") // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        oldage.setOnClickListener {
            val uri: Uri =
                Uri.parse("https://www.google.com/maps/search/oldage+homes+near+me") // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        pharmacies.setOnClickListener {
            val uri: Uri =
                Uri.parse("https://www.google.com/maps/search/pharmacy+near+me") // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

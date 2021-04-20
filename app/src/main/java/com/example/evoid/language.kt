package com.example.evoid

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import kotlinx.android.synthetic.main.activity_language.*
import kotlinx.android.synthetic.main.activity_my_profile.*

class language : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        cardViewLanguage.setBackgroundResource(R.drawable.cardcornersroundedall)
        supportActionBar!!.title = Html.fromHtml("<font color='#fffffff'> <small>LANGUAGE </font></small>")
        hindiTextLanguage.text = "हिंदी"

        hindiLanguage.setOnClickListener {
            displayTextLanguage.text = "* अनुवाद अभी दिशानिर्देशों पर ही उपलब्ध हैं।"
        }

        englishLanguage.setOnClickListener {
            displayTextLanguage.text = resources.getString(R.string.displayTextLanguage)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
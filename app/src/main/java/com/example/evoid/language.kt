package com.example.evoid

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Resources
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_language.*

class language : AppCompatActivity() {
    var mContext: Context? = null
    var flag = 0
    //var resources: Resources? = null
    lateinit var res:Resources
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        cardViewLanguage.setBackgroundResource(R.drawable.cardcornersroundedall)
        supportActionBar!!.title = Html.fromHtml("<font color='#fffffff'> <small>LANGUAGE </font></small>")
        hindiTextLanguage.text = "हिंदी"
        callFirestoreFunction()


        hindiLanguage.setOnClickListener {
            firestoreClass().updateLanguage("hi")
            callFirestoreFunction()

        }

        englishLanguage.setOnClickListener {
            firestoreClass().updateLanguage("en")
            callFirestoreFunction()
        }


    }




    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    fun callFirestoreFunction(){
        firestoreClass().loadLanguagePage(this,
            hindiTextLanguage,
            hindiLanguage,
            englishTextLanguage,
            englishLanguage,
            displayTextLanguage,
            resources.getDrawable(R.drawable.indiaflagbordered),
            resources.getDrawable(R.drawable.ukflagbordered),
            resources.getDrawable(R.drawable.indiaflag),
            resources.getDrawable(R.drawable.ukflag),
            resources.getColor(R.color.orangered),
            resources.getColor(R.color.white))
    }



}
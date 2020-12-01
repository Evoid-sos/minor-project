package com.example.evoid

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new_guidelines.*


class newGuidelines : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_guidelines)
        val actionbar = supportActionBar
        //set actionbar title
        actionbar!!.title = Html.fromHtml("<font color='#000000'>GUIDELINES </font>");
        //set back button
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
        val colorDrawable = ColorDrawable(Color.parseColor("#FFFFFF"))

        // Set BackgroundDrawable

        // Set BackgroundDrawable
        actionbar!!.setBackgroundDrawable(colorDrawable)
        fireGuide.setOnClickListener()
        {
            guideText.text =resources.getString(R.string.fire_guidelines_details)

        }
        medicalGuide.setOnClickListener()
        {
            guideText.text=resources.getString(R.string.medic_guidelines_details)

        }
        accidentGuide.setOnClickListener()
        {
            guideText.text=resources.getString(R.string.accident_guidelines_details)
        }
        crimeGuide.setOnClickListener()
        {
            guideText.text= resources.getString(R.string.crime_guidelines_details)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

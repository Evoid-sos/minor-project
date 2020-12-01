package com.example.evoid

import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_new_guidelines.*


class newGuidelines : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_guidelines)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar!!.title = Html.fromHtml("<font color='#fffffff'>GUIDELINES </font>")
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

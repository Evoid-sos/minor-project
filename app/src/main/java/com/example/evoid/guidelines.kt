package com.example.evoid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ScrollView
import kotlinx.android.synthetic.main.activity_guidelines.*

class guidelines : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guidelines)
        title = "Guidelines"

        fire.setOnClickListener() {

            guideText.text =resources.getString(R.string.fire_guidelines_details)

        }
        medic.setOnClickListener()
        {
            guideText.text=resources.getString(R.string.medic_guidelines_details)
        }
        accident.setOnClickListener()
        {
            guideText.text=resources.getString(R.string.accident_guidelines_details)
        }
        crime.setOnClickListener()
        {
            guideText.text= resources.getString(R.string.crime_guidelines_details)
        }

    }
}
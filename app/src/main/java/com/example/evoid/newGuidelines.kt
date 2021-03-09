package com.example.evoid

import android.speech.tts.TextToSpeech
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.OnPausedListener
import kotlinx.android.synthetic.main.activity_new_guidelines.*
import java.util.*


class newGuidelines : AppCompatActivity() {
    lateinit var mTTS:TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_guidelines)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar!!.title = Html.fromHtml("<font color='#fffffff'>GUIDELINES </font>")
        fun TxtToSpeech()
        {
            mTTS= TextToSpeech(applicationContext, TextToSpeech.OnInitListener { status ->
                if (status != TextToSpeech.ERROR)
                {
                    mTTS.language= Locale.US
                }
            })
           startSpeak.setOnClickListener {
               val toSpeak = guideText.text.toString()
               if (toSpeak == "")
               {
                   Toast.makeText(this,"Nothing To Speak",Toast.LENGTH_SHORT).show()
               }
               else
                   mTTS.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null)
           }
            stopSpeak.setOnClickListener {
                if (mTTS.isSpeaking)
                {
                    mTTS.stop()
                }
                else
                {
                    Toast.makeText(this,"Not Speaking",Toast.LENGTH_SHORT).show()
                }
            }
        }
        fireGuide.setOnClickListener()
        {
            guideText.text =resources.getString(R.string.fire_guidelines_details)
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            TxtToSpeech()
        }
        medicalGuide.setOnClickListener()
        {
            guideText.text=resources.getString(R.string.medic_guidelines_details)
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            TxtToSpeech()
        }
        accidentGuide.setOnClickListener()
        {
            guideText.text=resources.getString(R.string.accident_guidelines_details)
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            TxtToSpeech()
        }
        crimeGuide.setOnClickListener()
        {
            guideText.text= resources.getString(R.string.crime_guidelines_details)
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            TxtToSpeech()
        }
    }

    override fun onPause() {
        if (mTTS.isSpeaking)
        {
            mTTS.stop()
        }
        super.onPause()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

package com.example.evoid

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_new_guidelines.*
import java.util.*


class newGuidelines : AppCompatActivity() {

    lateinit var mTTS:TextToSpeech
    var flag=0
    var locale = Locale("hi_IN")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_guidelines)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar!!.title = Html.fromHtml("<font color='#fffffff'>GUIDELINES </font>")


        fun TxtToSpeechEnglish()
        {
            mTTS= TextToSpeech(applicationContext) { status ->
                if (status != TextToSpeech.ERROR) {
                    mTTS.language = locale
                    mTTS.setLanguage(locale)
                }
            }
            startSpeak.setOnClickListener {
               val toSpeak = guideText.text.toString()
               if (toSpeak == "")
               {
                   Toast.makeText(this, "Nothing To Speak", Toast.LENGTH_SHORT).show()
               }
               else
                   mTTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
           }
            stopSpeak.setOnClickListener {
                stopAudio()
            }
        }

        fireGuide.setOnClickListener()
        {
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            firestoreClass().loadGuidelines(guideText, "fire")
            TxtToSpeechEnglish()
        }
        medicalGuide.setOnClickListener()
        {
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            firestoreClass().loadGuidelines(guideText, "medical")
        }

        accidentGuide.setOnClickListener()
        {
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            firestoreClass().loadGuidelines(guideText, "accident")
        }


        crimeGuide.setOnClickListener()
        {
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            firestoreClass().loadGuidelines(guideText, "crime")
        }
    }



    override fun onPause() {
        if (mTTS.isSpeaking)
        {
            mTTS.stop()
        }
        super.onPause()
    }

    private fun stopAudio()
    {
        if (mTTS.isSpeaking)
        {
            mTTS.stop()
        }
        else
        {
            Toast.makeText(this, "Not Speaking", Toast.LENGTH_SHORT).show()
        }
    }





    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}


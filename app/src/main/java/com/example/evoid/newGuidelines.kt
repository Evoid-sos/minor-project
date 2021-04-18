package com.example.evoid

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.android.synthetic.main.activity_new_guidelines.*
import java.util.*


class newGuidelines : AppCompatActivity() {
    lateinit var mTTS:TextToSpeech
    var english=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_guidelines)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar!!.title = Html.fromHtml("<font color='#fffffff'>GUIDELINES </font>")

        fun englishToHindi(){
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.HINDI)
            .build()
        val englishHindiTranslator = Translation.getClient(options)
        }

        fun hindiToEnglish(){
            val options = TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.HINDI)
                .setTargetLanguage(TranslateLanguage.ENGLISH)
                .build()
            val hindiEnglishTranslator = Translation.getClient(options)
        }

        fun TxtToSpeechEnglish()
        {
            mTTS= TextToSpeech(applicationContext) { status ->
                if (status != TextToSpeech.ERROR) {
                    mTTS.language = Locale.US
                }
            }
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
                stopAudio()
            }
        }

        fireGuide.setOnClickListener()
        {
            guideText.text =resources.getString(R.string.fire_guidelines_details)
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            TxtToSpeechEnglish()
        }
        medicalGuide.setOnClickListener()
        {
            guideText.text=resources.getString(R.string.medic_guidelines_details)
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            TxtToSpeechEnglish()
        }
        accidentGuide.setOnClickListener()
        {
            guideText.text=resources.getString(R.string.accident_guidelines_details)
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            TxtToSpeechEnglish()
        }
        crimeGuide.setOnClickListener()
        {
            guideText.text= resources.getString(R.string.crime_guidelines_details)
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            TxtToSpeechEnglish()
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
            Toast.makeText(this,"Not Speaking",Toast.LENGTH_SHORT).show()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

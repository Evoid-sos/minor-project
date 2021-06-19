package com.example.evoid

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

import kotlinx.android.synthetic.main.activity_new_guidelines.*
import java.util.*


class newGuidelines : AppCompatActivity() {
    private val mFireStore = FirebaseFirestore.getInstance()

    lateinit var mTTS:TextToSpeech
    var flag=0
    var locale = Locale("hi_IN")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_guidelines)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        getActionBarLang(supportActionBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        firestoreClass().loadGuidelinesPage(textView5, medicalGuide, accidentGuide, fireGuide, crimeGuide, startSpeak, stopSpeak)


        fun TxtToSpeechEnglish()
        {
            mTTS= TextToSpeech(applicationContext) { status ->
                if (status != TextToSpeech.ERROR) {
                    mTTS.language = locale
                    mTTS.language = locale
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
            firestoreClass().loadGuidelines(guideText, "fire", textView5)
            TxtToSpeechEnglish()
        }
        medicalGuide.setOnClickListener()
        {
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            firestoreClass().loadGuidelines(guideText, "medical", textView5)
        }

        accidentGuide.setOnClickListener()
        {
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            firestoreClass().loadGuidelines(guideText, "accident", textView5)
        }


        crimeGuide.setOnClickListener()
        {
            startSpeak.visibility=View.VISIBLE
            stopSpeak.visibility=View.VISIBLE
            firestoreClass().loadGuidelines(guideText, "crime", textView5)
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

    fun getActionBarLang(supportActionBar: ActionBar?) {
        var loggedInUser: User
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener { document ->
                loggedInUser = document.toObject(User::class.java)!!
                if (loggedInUser.lang == "en") {

                    supportActionBar?.title = Html.fromHtml("<font color='#fffffff'>GUIDELINES </font>")

                }
                if (loggedInUser.lang == "hi") {

                    supportActionBar?.title = Html.fromHtml("<font color='#fffffff'>दिशा-निर्देश </font>")

                }
            }

    }

    fun getCurrentUserId():String
    {
        val uid = Firebase.auth.currentUser!!.uid
        return uid
    }


}


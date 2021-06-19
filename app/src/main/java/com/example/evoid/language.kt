package com.example.evoid

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Resources
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_language.*

class language : AppCompatActivity() {
    var mContext: Context? = null
    private val mFireStore = FirebaseFirestore.getInstance()
    var flag = 0
    //var resources: Resources? = null
    lateinit var res:Resources
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        getActionBarLang(supportActionBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        cardViewLanguage.setBackgroundResource(R.drawable.cardcornersroundedall)


        //supportActionBar!!.title = Html.fromHtml("<font color='#fffffff'> <small>LANGUAGE </font></small>")
        hindiTextLanguage.text = "हिंदी"
        callFirestoreFunction()


        hindiLanguage.setOnClickListener {

            firestoreClass().updateLanguage("hi")
            callFirestoreFunction()
            startActivity(Intent(this, MainActivity2::class.java))
            finish()

        }

        englishLanguage.setOnClickListener {

            firestoreClass().updateLanguage("en")
            callFirestoreFunction()
            startActivity(Intent(this, MainActivity2::class.java))
            finish()

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


    fun getActionBarLang(supportActionBar: ActionBar?) {
        var loggedInUser: User
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener { document ->
                loggedInUser = document.toObject(User::class.java)!!
                if (loggedInUser.lang == "en") {

                    supportActionBar?.title = Html.fromHtml("<font color='#fffffff'> <small>APP LANGUAGE </font></small>")

                }
                if (loggedInUser.lang == "hi") {

                    supportActionBar?.title = Html.fromHtml("<font color='#fffffff'> <small>ऐप की भाषा </font></small>")

                }
            }

    }

    fun getCurrentUserId():String
    {
        val uid = Firebase.auth.currentUser!!.uid
        return uid
    }





}
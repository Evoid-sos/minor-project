package com.example.evoid

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_faqs.*
import kotlinx.android.synthetic.main.activity_nearby_help.*

class nearbyHelp : AppCompatActivity() {
    private val mFireStore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_help)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        getActionBarLang(supportActionBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        buttonSetVisibilityHere()
        policeStations.setOnClickListener {
            val uri: Uri =
                Uri.parse("https://www.google.com/maps/search/police+stations+near+me") // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        hospitals.setOnClickListener {
            val uri: Uri =
                Uri.parse("https://www.google.com/maps/search/hospitals+near+me") // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        fireStations.setOnClickListener {
            val uri: Uri =
                Uri.parse("https://www.google.com/maps/search/fire+stations+near+me") // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        oldage.setOnClickListener {
            val uri: Uri =
                Uri.parse("https://www.google.com/maps/search/oldage+homes+near+me") // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        pharmacies.setOnClickListener {
            val uri: Uri =
                Uri.parse("https://www.google.com/maps/search/pharmacy+near+me") // missing 'http://' will cause crashed
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
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

                    supportActionBar?.title = Html.fromHtml("<font color='#fffffff'>FIND HELP NEAR ME </font>")

                }
                if (loggedInUser.lang == "hi") {

                    supportActionBar?.title = Html.fromHtml("<font color='#fffffff'>आस-पास सहायता प्राप्त करें </font>")

                }
            }

    }

    fun getCurrentUserId():String
    {
        val uid = Firebase.auth.currentUser!!.uid
        return uid
    }


    fun buttonSetVisibilityHere() {
        var loggedInUser: User
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener { document ->
                loggedInUser = document.toObject(User::class.java)!!
                if (loggedInUser.lang == "en") {

                    textView3.setText(R.string.policeStations)
                    textView4.setText(R.string.hospitals)
                    textView5.setText(R.string.fireStations)
                    textView6.setText(R.string.oldAgeHomes)
                    textView7.setText(R.string.pharmacies)

                }
                if (loggedInUser.lang == "hi") {

                    textView3.setText(R.string.policeStationsHindi)
                    textView4.setText(R.string.hospitalsHindi)
                    textView5.setText(R.string.fireStationsHindi)
                    textView6.setText(R.string.oldAgeHomesHindi)
                    textView7.setText(R.string.pharmaciesHindi)

                }
            }

    }
}

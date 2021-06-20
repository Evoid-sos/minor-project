package com.example.evoid

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_faqs.*
import kotlinx.android.synthetic.main.fragment_mental_health_awareness.*

class faqs : AppCompatActivity() {
    private val mFireStore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faqs)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        getActionBarLang(supportActionBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        buttonSetVisibilityHere()


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

                    supportActionBar?.title = Html.fromHtml("<font color='#fffffff'>INSTRUCTIONS </font>")

                }
                if (loggedInUser.lang == "hi") {

                    supportActionBar?.title = Html.fromHtml("<font color='#fffffff'>ऐप उपयोग निर्देश </font>")

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

                    val one="<b><i>\u2022 Add up to 5 trusted contacts from the 'Emergency Contacts' tab.<br><br>" +
                            "<b><i>\u2022 Use the 'Panic Button' to send location to trusted contacts through SMS.<br><br>" +
                            "<b><i>\u2022 When the Panic Button is pressed, you would be prompted to click picture from back and front camera. These images would be sent to your trusted contacts as link via SMS.<br><br>" +
                            "<b><i>\u2022 You can check your current location using the 'My Location' option.<br><br>" +
                            "<b><i>\u2022 'Start Alarm' option can be used to raise an alarm to alert people around you.<br><br>" +
                            "<b><i>\u2022 You can search for nearby Police Stations, Hospitals, Fire Station, Old Age Shelters and Pharmacies using the 'Find Help Near Me' menu option.<br><br>" +
                            "<b><i>\u2022 You can update your username and phone number by tapping on the image displayed on top of the menu."
                    quesOne.text=Html.fromHtml(one)

                }
                if (loggedInUser.lang == "hi") {

                    val one="<b><i>\u2022 'आपातकालीन संपर्क' टैब से अधिकतम 5 विश्वसनीय संपर्क जोड़ें।<br><br>" +
                            "<b><i>\u2022 विश्वसनीय संपर्कों को एसएमएस के माध्यम से स्थान भेजने के लिए 'दहशत बटन' का उपयोग करें।<br><br>" +
                            "<b><i>\u2022 जब दहशत बटन दबाया जाता है, तो आपको पीछे और सामने के कैमरे से तस्वीर क्लिक करने के लिए कहा जाएगा। ये चित्र आपके विश्वसनीय संपर्कों को एसएमएस के माध्यम से लिंक के रूप में भेजे जाएंगे।<br><br>" +
                            "<b><i>\u2022 आप 'मेरा वर्तमान स्थान' विकल्प का उपयोग करके अपने वर्तमान स्थान की जांच कर सकते हैं।<br><br>" +
                            "<b><i>\u2022 अपने आसपास के लोगों को सचेत करने के लिए और अलार्म शुरू करने के लिए 'अलार्म शुरू करें' विकल्प का उपयोग किया जा सकता है।<br><br>" +
                            "<b><i>\u2022 आप 'आस-पास सहायता प्राप्त करें' मेन्यू विकल्प का उपयोग करके आस-पास के पुलिस स्टेशनों, अस्पतालों, फायर स्टेशन, वृद्धावस्था आश्रयों और फार्मेसियों की खोज कर सकते हैं।<br><br>" +
                            "<b><i>\u2022 आप मेनू के शीर्ष पर प्रदर्शित छवि पर टैप करके अपना उपयोगकर्ता नाम और फ़ोन नंबर अपडेट कर सकते हैं।"
                    quesOne.text=Html.fromHtml(one)


                }
            }

    }


}
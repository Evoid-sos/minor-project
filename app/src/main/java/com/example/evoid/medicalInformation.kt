package com.example.evoid

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_medical_information.*

class medicalInformation : AppCompatActivity() {
    private val mFireStore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_information)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        getActionBarLang(supportActionBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        cardViewMedicalInformation.setBackgroundResource(R.drawable.cardcorners)

        firestoreClass().loadMedicalInformation(nameMedicalInformation, addressMedicalInformation, bloodTypeMedicalInformation,
            allergiesMedicalInformation, medicationsMedicalInformation, medicalNotesMedicalInformation, organDonorMedicalInformation)

        //val name = firestoreClass().getname(nameMedicalInformation)
        saveMedicalInformation.setOnClickListener {
            firestoreClass().updateMedicalInformation(addressMedicalInformation.text.toString(),
                bloodTypeMedicalInformation.text.toString(), medicationsMedicalInformation.text.toString(),
                medicalNotesMedicalInformation.text.toString(), organDonorMedicalInformation.text.toString(),
                nameMedicalInformation.text.toString(), allergiesMedicalInformation.text.toString())
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
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

                    supportActionBar?.title = Html.fromHtml("<font color='#fffffff'> <small>MY MEDICAL INFORMATION </font></small>")

                }
                if (loggedInUser.lang == "hi") {

                    supportActionBar?.title = Html.fromHtml("<font color='#fffffff'> <small>मेरी चिकित्सा जानकारी </font></small>")

                }
            }

    }

    fun getCurrentUserId():String
    {
        val uid = Firebase.auth.currentUser!!.uid
        return uid
    }

}
package com.example.evoid

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_medical_information.*

class medicalInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_information)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = Html.fromHtml("<font color='#fffffff'> <small>MY MEDICAL INFORMATION </font></small>")
        cardViewMedicalInformation.setBackgroundResource(R.drawable.cardcorners)

        firestoreClass().loadMedicalInformation(nameMedicalInformation, addressMedicalInformation, bloodTypeMedicalInformation,
            allergiesMedicalInformation, medicationsMedicalInformation, medicalNotesMedicalInformation, organDonorMedicalInformation)

        //val name = firestoreClass().getname(nameMedicalInformation)
        saveMedicalInformation.setOnClickListener {
            firestoreClass().updateMedicalInformation(addressMedicalInformation.text.toString(),
                bloodTypeMedicalInformation.text.toString(), medicationsMedicalInformation.text.toString(),
                medicalNotesMedicalInformation.text.toString(), organDonorMedicalInformation.text.toString(),
                nameMedicalInformation.text.toString())
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
        }


    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
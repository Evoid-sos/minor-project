package com.example.evoid

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.telephony.SmsManager
import android.text.Html
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.ktx.Firebase
import de.hdodenhof.circleimageview.CircleImageView
import java.lang.Exception

class firestoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()
    var location: locationDetails? = null
    var lattitude: Double? = null
    var longitude: Double? = null
    var lang = ""
    var images:pictures?=null
    var currContact:String?=null
    var currContact1:String?=null
    fun getLoggedInUser(activity: Activity) {
        val navigationView : NavigationView = activity.findViewById(R.id.nav_view)
        val headerView : View = navigationView.getHeaderView(0)
        val image = headerView.findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.profileIconNav)
        val name = headerView.findViewById<TextView>(R.id.userDetails)

        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener { document ->
                val loggedInUser = document.toObject(User::class.java)!!
                updateNavBar(activity, loggedInUser, name, image)
            }
    }

    fun updateNavBar(
        activity: Activity,
        user: User,
        name: TextView,
        image: de.hdodenhof.circleimageview.CircleImageView
    )
    {

        Glide
            .with(activity)
            .load(user.image)
            .fitCenter()
            .placeholder(R.drawable.logo1)
            .into(image)

        name.text = user.firstName + " " + user.lastName


    }

    fun registerUser(activity: RegisterUser, userInfo: User)
    {
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                Toast.makeText(activity, "registered", Toast.LENGTH_SHORT).show()
            }
    }

    fun registerUserViaGoogle(activity: start_page, userInfo: User)
    {
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                //Toast.makeText(activity, "registered", Toast.LENGTH_SHORT).show()
            }
    }



    fun getCurrentUserId():String
    {
        val uid = Firebase.auth.currentUser!!.uid
        return uid
    }

    fun addContact(contactInfo: ContactsDetails, phoneId: String)
    {
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .collection(constants.ContactsDetails)
            .document(phoneId)
            .set(contactInfo, SetOptions.merge())
    }

    fun updateLocation(location: locationDetails)
    {
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .collection(constants.locationDetails)
            .document(getCurrentUserId())
            .set(location, SetOptions.merge())
    }

    fun getLocation()
    {

        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .collection(constants.locationDetails)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener { document->
                location = document.toObject(locationDetails::class.java)
                lattitude = location?.latitude?.toDoubleOrNull()
                longitude = location?.longitude?.toDoubleOrNull()
            }
    }


    fun getContacts(activity: Activity){
        try{
        getLocation()
        val handler = android.os.Handler()
        handler.postDelayed({ print("") }, 500)
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .collection(constants.ContactsDetails)
            .get().addOnSuccessListener { results ->
                for (documents in results) {
                    val handle = android.os.Handler()
                    handle.postDelayed({ print("") }, 500)
                    currContact = documents.get("contactPhoneNumber").toString()
                    val mySmsManager = SmsManager.getDefault()

                    if (lattitude != null && longitude != null){
                        mySmsManager.sendTextMessage(currContact,
                            null,
                            constants.msg + "\n" + "https://maps.google.com/maps?daddr=$lattitude,$longitude",
                            null,
                            null)
                    }
                    else{
                        mySmsManager.sendTextMessage(currContact,
                            null,
                            constants.msg + "\n" + "https://maps.google.com/maps?daddr=28.5979774,77.0503338",
                            null,
                            null)
                    }


                }

                Toast.makeText(activity, "Location sent", Toast.LENGTH_SHORT).show()

            }}
        catch (e: java.lang.NullPointerException)
        {
            print("ExceptionCaught")
        }
    }

    fun loadMyProfile(
        activity: Activity,
        img: CircleImageView,
        email: EditText,
        name: EditText,
        mobile: EditText
    )
    {
        var loggedInUser:User
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener { document ->
                loggedInUser = document.toObject(User::class.java)!!
                Glide
                    .with(activity)
                    .load(loggedInUser.image)
                    .fitCenter()
                    .placeholder(R.drawable.placeholder3)
                    .into(img);
                email.setText(loggedInUser.emailId)
                name.setText(loggedInUser.firstName + " " + loggedInUser.lastName)
                mobile.setText(loggedInUser.phoneNumber.toString())
            }

    }

    fun updateMyProfile(name: String, mobile: String)
    {

        val nameParts = name.split(" ")
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .update(mapOf(
                "firstName" to nameParts[0],
                "lastName" to nameParts[1],
                "phoneNumber" to mobile.toLong(),
            ))
    }

    fun updateFirestoreImage(imgUrl: String)
    {
        mFireStore.collection(constants.USERS).document(getCurrentUserId()).update(mapOf(
            "image" to imgUrl
        ))
    }

    fun saveEmergencyImage(pictures: pictures, imgBack: Uri, imgFront: Uri, activity: Activity)
    {
        mFireStore.collection(constants.USERS).document(getCurrentUserId()).collection(constants.pictures).document(
            getCurrentUserId())
            .set(pictures, SetOptions.merge())
        sendPicturesLink(pictures,activity)

    }

    private fun sendPicturesLink(pictures: pictures, activity: Activity) {
        val imgBack = pictures.back
        var imgFront = pictures.front
        val handler = android.os.Handler()
        handler.postDelayed({ print("") }, 500)
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .collection(constants.ContactsDetails)
            .get().addOnSuccessListener { results ->
                for (documents in results) {
                    val handle = android.os.Handler()
                    handle.postDelayed({ print("") }, 1500)
                    currContact = documents.get("contactPhoneNumber").toString()
                    val mySmsManager = SmsManager.getDefault()
                    val parts: ArrayList<String> = mySmsManager.divideMessage(imgBack)
                    val parts1: ArrayList<String> = mySmsManager.divideMessage(imgFront)
                    mySmsManager.sendTextMessage(currContact,
                        null,
                        constants.msg1 ,
                        null,
                        null)
                    mySmsManager.sendMultipartTextMessage(currContact,
                        null,
                        parts,
                        null,
                        null)
                    mySmsManager.sendMultipartTextMessage(currContact,
                        null,
                        parts1,
                        null,
                        null)
                }

                Toast.makeText(activity, "Pictures sent", Toast.LENGTH_SHORT).show()

            }


    }



    fun updateLanguage(s: String) {
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .update(mapOf(
                "lang" to s
            ))
    }

    fun loadLanguagePage(
        activity: Activity,
        hindiTextLanguage: TextView,
        hindiLanguage: CircleImageView,
        englishTextLanguage: TextView,
        englishLanguage: CircleImageView,
        displayTextLanguage: TextView,
        drawable: Drawable,
        drawable1: Drawable,
        drawable2: Drawable,
        drawable4: Drawable,
        color: Int,
        color1: Int
    ) {
        var loggedInUser:User
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener {document->
                loggedInUser = document.toObject(User::class.java)!!
                if (loggedInUser.lang == "en")
                {
                    changeContentToEnglish(activity,
                        hindiLanguage,
                        hindiTextLanguage,
                        englishLanguage,
                        englishTextLanguage,
                        displayTextLanguage,
                        drawable,
                        drawable1,
                        drawable2,
                        drawable4,
                        color,
                        color1)
                }
                if (loggedInUser.lang == "hi")
                {
                    changeContentToHindi(activity,
                        hindiLanguage,
                        hindiTextLanguage,
                        englishLanguage,
                        englishTextLanguage,
                        displayTextLanguage,
                        drawable,
                        drawable1,
                        drawable2,
                        drawable4,
                        color,
                        color1)
                }
            }


    }

    @SuppressLint("ResourceAsColor")
    private fun changeContentToHindi(
        activity: Activity,
        hindiLanguage: CircleImageView,
        hindiTextLanguage: TextView,
        englishLanguage: CircleImageView,
        englishTextLanguage: TextView,
        displayTextLanguage: TextView,
        drawable: Drawable,
        drawable1: Drawable,
        drawable2: Drawable,
        drawable4: Drawable,
        color: Int,
        color1: Int
    ) {
        hindiTextLanguage.setTextColor(color)
        englishTextLanguage.setTextColor(color1)
        displayTextLanguage.setText(R.string.displayTextLanguageH)
        hindiLanguage.setImageDrawable(drawable)
        englishLanguage.setImageDrawable(drawable4)
    }


    @SuppressLint("ResourceAsColor")
    private fun changeContentToEnglish(
        activity: Activity,
        hindiLanguage: CircleImageView,
        hindiTextLanguage: TextView,
        englishLanguage: CircleImageView,
        englishTextLanguage: TextView,
        displayTextLanguage: TextView,
        drawable: Drawable,
        drawable1: Drawable,
        drawable2: Drawable,
        drawable4: Drawable,
        color: Int,
        color1: Int
    ) {
        hindiTextLanguage.setTextColor(color1)
        englishTextLanguage.setTextColor(color)
        displayTextLanguage.setText(R.string.displayTextLanguage)
        hindiLanguage.setImageDrawable(drawable2)
        englishLanguage.setImageDrawable(drawable1)
    }

    fun loadGuidelines(guideText: TextView, button: String, textView5: TextView) {
        var loggedInUser: User
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener { document ->
                loggedInUser = document.toObject(User::class.java)!!
                if (loggedInUser.lang == "en") {


                    if (button == "fire")
                    {
                        guideText.setText(R.string.fire_guidelines_details)
                    }
                    if (button == "medical")
                    {
                        guideText.setText(R.string.medic_guidelines_details)
                    }
                    if (button == "accident")
                    {
                        guideText.setText(R.string.accident_guidelines_details)
                    }
                    if (button == "crime")
                    {
                        guideText.setText(R.string.crime_guidelines_details)
                    }

                }
                if (loggedInUser.lang == "hi") {


                    if (button == "fire")
                    {
                        guideText.setText(R.string.fire_guidelines_detailsH)
                    }
                    if (button == "medical")
                    {
                        guideText.setText(R.string.medic_guidelines_detailsH)
                    }
                    if (button == "accident")
                    {
                        guideText.setText(R.string.accident_guidelines_detailsH)
                    }
                    if (button == "crime")
                    {
                        guideText.setText(R.string.crime_guidelines_detailsH)
                    }

                }
            }
    }

    fun loadMedicalInformation(
        name: EditText,
        address: EditText,
        bloodType: EditText,
        allergies: EditText,
        medications: EditText,
        medicalNotes: EditText,
        organDonor: EditText
    ) {
        val data = hashMapOf("organDonor" to "no")
        getname(name)
        mFireStore.collection(constants.USERS)
                .document(getCurrentUserId())
                .collection(constants.MedicalDetails)
                .get().addOnSuccessListener { sub->
                if (sub.isEmpty){
                    mFireStore.collection(constants.USERS)
                        .document(getCurrentUserId())
                        .collection(constants.MedicalDetails)
                        .document(getCurrentUserId())
                        .set(data, SetOptions.merge())

                }
                else
                {

                    var loggedInUser: MedicalDetails? = null
                    mFireStore.collection(constants.USERS)
                        .document(getCurrentUserId())
                        .collection(constants.MedicalDetails)
                        .document(getCurrentUserId())
                        .get().addOnSuccessListener { document ->
                            loggedInUser = document.toObject(MedicalDetails::class.java)!!
                            getname(name)
                            address.setText(loggedInUser!!.address)
                            bloodType.setText(loggedInUser!!.bloodType)
                            allergies.setText(loggedInUser!!.allergies)
                            medications.setText(loggedInUser!!.medications)
                            medicalNotes.setText(loggedInUser!!.medicalNotes)
                            organDonor.setText(loggedInUser!!.organDonor)
                        }
                }
            }

    }

    fun updateMedicalInformation(
        address: String,
        bloodType: String,
        medications: String,
        medicalNotes: String,
        organDonor: String,
        name: String,
        allergies: String
    ) {

        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .collection(constants.MedicalDetails)
            .document(getCurrentUserId())
            .update(mapOf(
                "name" to name,
                "address" to address,
                "bloodType" to bloodType,
                "allergies" to allergies,
                "medications" to medications,
                "medicalNotes" to medicalNotes,
                "organDonor" to organDonor
            ))

    }

    fun getname(name: EditText) {
        var fullName:String = ""
        var loggedInUserName: User
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener { document->
                loggedInUserName = document.toObject(User::class.java)!!
                fullName = loggedInUserName.firstName + " " + loggedInUserName.lastName
                name.setText(fullName)
            }
    }

    fun loadGuidelinesPage(
        textView5: TextView?,
        medicalGuide: Button,
        accidentGuide: Button,
        fireGuide: Button,
        crimeGuide: Button,
        startSpeak: Button,
        stopSpeak: Button
    ) {
        var loggedInUser: User
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener { document ->
                loggedInUser = document.toObject(User::class.java)!!
                if (loggedInUser.lang == "en") {

                    if (textView5 != null) {
                        textView5.setText(R.string.tap_to_view_guidelines)
                        medicalGuide.setText(R.string.medical_n_emergency)
                        accidentGuide.setText(R.string.accident)
                        fireGuide.setText(R.string.fire)
                        crimeGuide.setText(R.string.crime)
                        startSpeak.setText(R.string.startAudio)
                        stopSpeak.setText(R.string.stopAudio)
                    }


                }
                if (loggedInUser.lang == "hi") {

                    if (textView5 != null) {
                        textView5.setText(R.string.tap_to_view_guidelines_hindi)
                        medicalGuide.setText(R.string.medical_n_emergency_hindi)
                        accidentGuide.setText(R.string.accident_hindi)
                        fireGuide.setText(R.string.fire_hindi)
                        crimeGuide.setText(R.string.crime_hindi)
                        startSpeak.setText(R.string.startAudioHindi)
                        stopSpeak.setText(R.string.stopAudioHindi)
                    }
                }
            }


    }

    fun loadCyberProtectionPage(textView6: TextView, reportLink: TextView) {
        var loggedInUser: User
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener { document ->
                loggedInUser = document.toObject(User::class.java)!!
                if (loggedInUser.lang == "en") {

                    textView6.setText(R.string.tap_the_image_to_view_guidelines)
                    reportLink.setText(R.string.report)


                }
                if (loggedInUser.lang == "hi") {

                    textView6.setText(R.string.tap_the_image_to_view_guidelines_hindi)
                    reportLink.setText(R.string.reportHindi)


                }
            }

    }

    fun loadCyberProtectionGuidelines(showGuidelines: TextView?) {
        var loggedInUser: User
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener { document ->
                loggedInUser = document.toObject(User::class.java)!!
                if (loggedInUser.lang == "en") {

                    showGuidelines!!.setText(R.string.cyber_security_guidelines_details)


                }
                if (loggedInUser.lang == "hi") {

                    showGuidelines!!.setText(R.string.cyber_security_guidelines_details_hindi)


                }
            }

    }


    fun changeMenuItemsLang(menu: Menu)
    {

        var loggedInUser: User
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener { document ->
                loggedInUser = document.toObject(User::class.java)!!
                if (loggedInUser.lang == "en") {

                    menu.findItem(R.id.nearhelpH).isVisible = false
                    menu.findItem(R.id.guidelinesH).isVisible = false
                    menu.findItem(R.id.covidH).isVisible = false
                    menu.findItem(R.id.cybercrimeH).isVisible = false
                    menu.findItem(R.id.medicalInfoH).isVisible = false
                    menu.findItem(R.id.appLanguageH).isVisible = false
                    menu.findItem(R.id.helpH).isVisible = false
                    menu.findItem(R.id.logoutH).isVisible = false

                }
                if (loggedInUser.lang == "hi") {

                    menu.findItem(R.id.nearhelp).isVisible = false
                    menu.findItem(R.id.guidelines).isVisible = false
                    menu.findItem(R.id.covid).isVisible = false
                    menu.findItem(R.id.cybercrime).isVisible = false
                    menu.findItem(R.id.medicalInfo).isVisible = false
                    menu.findItem(R.id.appLanguage).isVisible = false
                    menu.findItem(R.id.help).isVisible = false
                    menu.findItem(R.id.logout).isVisible = false

                }
            }
    }




}

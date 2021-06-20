package com.example.evoid
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_cybersecurity.*
import kotlinx.android.synthetic.main.fragment_emergency.*
import kotlinx.android.synthetic.main.fragment_mental_health_awareness.*
import kotlinx.android.synthetic.main.fragment_mental_health_awareness.view.*

class mentalHealthAwareness : Fragment() {

    private val mFireStore = FirebaseFirestore.getInstance()
    private val REQUEST_PHONE_CALL = 1
    private var fortis = 0
    private var icall = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_mental_health_awareness, container, false)
        buttonSetVisibilityHere()
        view.fortisHelpline.setOnClickListener {
            fortis=1
            askCallPermission()
        }

        view.fortisHelplineHindi.setOnClickListener {
            fortis=1
            askCallPermission()
        }

        view.Icallhelp.setOnClickListener {
            icall=1
            askCallPermission()
        }

        view.IcallhelpHindi.setOnClickListener {
            icall=1
            askCallPermission()
        }

        view.reportLink2.movementMethod= LinkMovementMethod.getInstance()
        return view

    }

    private fun askCallPermission() {
        if (ContextCompat.checkSelfPermission(activity as MainActivity2,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), REQUEST_PHONE_CALL)
        }
        else
        {
            makePhoneCall()
        }
    }

    private fun makePhoneCall() {
        var phoneNumber:Long = 0
        when
        {
            fortis == 1 ->
            {
                phoneNumber = 8376804102
            }
            icall ==1 -> {
                phoneNumber = 9152987821
            }
        }
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        if (requestCode == REQUEST_PHONE_CALL) {
            makePhoneCall()
        }
    }

    fun buttonSetVisibilityHere() {
        var loggedInUser: User
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener { document ->
                loggedInUser = document.toObject(User::class.java)!!
                if (loggedInUser.lang == "en") {

                    fortisHelpline.isVisible = true
                    Icallhelp.isVisible = true
                    fortisHelpline.isClickable = true
                    Icallhelp.isClickable = true
                    fortisHelplineHindi.isVisible = false
                    IcallhelpHindi.isVisible = false
                    fortisHelplineHindi.isClickable = false
                    IcallhelpHindi.isClickable = false
                    reportLink2.setText(R.string.moreHelp)
                    textView4.setText(R.string.mentalWellbeing)
                    textView7.setText(R.string.steps)

                }
                if (loggedInUser.lang == "hi") {

                    fortisHelplineHindi.isVisible = true
                    IcallhelpHindi.isVisible = true
                    fortisHelplineHindi.isClickable = true
                    IcallhelpHindi.isClickable = true
                    fortisHelpline.isVisible = false
                    Icallhelp.isVisible = false
                    fortisHelpline.isClickable = false
                    Icallhelp.isClickable = false
                    reportLink2.setText(R.string.moreHelpHindi)
                    textView4.setText(R.string.mentalWellbeingHindi)
                    textView7.setText(R.string.stepsHindi)

                }
            }

    }

    fun getCurrentUserId():String
    {
        val uid = Firebase.auth.currentUser!!.uid
        return uid
    }
}

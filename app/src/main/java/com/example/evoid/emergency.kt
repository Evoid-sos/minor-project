package com.example.evoid

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.telephony.SmsManager
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_emergency.*
import kotlinx.android.synthetic.main.fragment_emergency.view.*
import kotlinx.coroutines.delay
import java.util.ArrayList
import java.util.jar.Manifest


class emergency : Fragment() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    val REQUEST_PHONE_CALL = 1
    val REQUEST_SEND_SMS = 100
    val REQUEST_LOCATION = 2
    var fire = 0
    var women = 0
    var police = 0
    var ambulance = 0
    private var flag = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_emergency, container, false)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity as MainActivity2)

        view.play.setOnClickListener { view ->
            if (flag == 0) {
                flag = 1
                play.visibility = View.INVISIBLE
                pause.visibility = View.VISIBLE

            }
        }
        view.pause.setOnClickListener(){
            if (flag==1)
            {
                flag=0
                pause.visibility = View.INVISIBLE
                play.visibility = View.VISIBLE
            }
        }

        view.fireHelpline.setOnClickListener { view ->
            fire=1
            askPermission()
        }
        view.womenHelpline.setOnClickListener { view ->
            women = 1
            askPermission()
        }
        view.policeHelpline.setOnClickListener { view ->
            police=1
            askPermission()
        }
        view.ambulanceHelpline.setOnClickListener { view ->
            ambulance=1
            askPermission()

        }

        view.emergencybutton.setOnClickListener {

            askLocationPerm()



        }
        // Return the fragment view/layout
        return view
    }

    private fun askSendSMSPerm() {
        if(ActivityCompat.checkSelfPermission
                (activity as MainActivity2,android.Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(arrayOf(android.Manifest.permission.SEND_SMS),REQUEST_SEND_SMS)

        }
        else
        {
            sendSMSToContacts()
        }
    }

    private fun askLocationPerm() {
        if(ActivityCompat.checkSelfPermission(activity as MainActivity2,android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),REQUEST_LOCATION)

        }
        else
        {
            getLocationCurrent()
        }
    }

    private fun getLocationCurrent() {
        val request = LocationRequest()
        request.interval = 10000
        request.fastestInterval = 5000
        request.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        val permission = ActivityCompat.checkSelfPermission(activity as MainActivity2, android.Manifest.permission.ACCESS_FINE_LOCATION)
        if (permission == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(request, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    val location: Location? = locationResult.lastLocation
                    if (location != null) {
                        val loc = locationDetails(location.latitude.toString(),location.longitude.toString())
                        firestoreClass().updateLocation(loc)
                    }
                }
            }, null)

    }
        askSendSMSPerm()
    }

    private fun sendSMSToContacts() {
        firestoreClass().getContacts(activity as MainActivity2)
    }

    private fun askPermission() {
        if (ActivityCompat.checkSelfPermission(activity as MainActivity2, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE),REQUEST_PHONE_CALL)
        }
        else
        {
            makePhoneCall()
        }

    }

    private fun makePhoneCall() {
        var phoneNumber:Long = 0
        if (fire ==1)
        {
            phoneNumber = 9760003923
        }
        else if (police ==1)
        {
            phoneNumber = 9560983181
        }
        else if (women ==1)
        {
            phoneNumber = 9560983181
        }
        else if (ambulance ==1)
        {
            phoneNumber = 9560983181
        }

        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PHONE_CALL )
        {
           makePhoneCall()

        }
        if (requestCode == REQUEST_LOCATION)
        {
            getLocationCurrent()

        }
        if (requestCode == REQUEST_SEND_SMS)
        {
            sendSMSToContacts()

        }
    }




}
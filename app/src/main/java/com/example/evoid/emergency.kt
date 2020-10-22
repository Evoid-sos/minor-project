package com.example.evoid

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_emergency.view.*
import java.util.jar.Manifest


class emergency : Fragment() {
    val REQUEST_PHONE_CALL = 1
    var fire = 0
    var women = 0
    var police = 0
    var ambulance = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_emergency, container, false)


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
        // Return the fragment view/layout
        return view
    }

    private fun askPermission() {
        if (ActivityCompat.checkSelfPermission(activity as MainActivity2, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(activity as MainActivity2,
                arrayOf(android.Manifest.permission.CALL_PHONE),REQUEST_PHONE_CALL)
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
            phoneNumber = 9560983181
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
    }


}
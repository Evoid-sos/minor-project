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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_emergency, container, false)

        view.fireHelpline.setOnClickListener { view ->
            makePhoneCall()
        }
        view.womenHelpline.setOnClickListener { view ->
            makePhoneCall()
        }
        view.policeHelpline.setOnClickListener { view ->
            makePhoneCall()
        }
        view.ambulanceHelpline.setOnClickListener { view ->
            if (ActivityCompat.checkSelfPermission(activity as MainActivity2, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(activity as MainActivity2,
                    arrayOf(android.Manifest.permission.CALL_PHONE),REQUEST_PHONE_CALL)
            }
            else
            {
                makePhoneCall()
            }

        }
        // Return the fragment view/layout
        return view
    }

    private fun makePhoneCall() {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:9560983181")
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
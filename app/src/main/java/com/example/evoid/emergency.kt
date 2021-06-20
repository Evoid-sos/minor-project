package com.example.evoid

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.location.Location
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.preference.PreferenceManager
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.camera.core.Camera
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_emergency.*
import kotlinx.android.synthetic.main.fragment_emergency.view.*
import java.lang.Exception


class emergency : Fragment() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var mPlayer: MediaPlayer? = null

    private val mFireStore = FirebaseFirestore.getInstance()
    val REQUEST_PHONE_CALL = 1
    val REQUEST_SEND_SMS = 100
    val REQUEST_CAMERA = 3
    val REQUEST_WRITE_EXTERNAL = 5
    var camera: Camera? = null
    val REQUEST_LOCATION = 2
    var fire = 0
    var women = 0
    var police = 0
    var ambulance = 0
    private var flag = 0
    private var flashflag =0
    var flashLightStatus: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_emergency, container, false)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity as MainActivity2)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity)
        if (sharedPreferences.getBoolean("IS_FIRST_TIME", true)) {
            val builder = AlertDialog.Builder(activity)
            //set title for alert dialog
            builder.setTitle("WELCOME")
            //set message for alert dialog
            builder.setMessage(R.string.dialogMessage)

            //performing positive action
            builder.setPositiveButton("OK"){ _, _ ->
                Toast.makeText(activity,"Please swipe right to add emergency contacts",Toast.LENGTH_LONG).show()
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()
            //show your dialog here
            //...
            //change the value of your sharedPreferences
            sharedPreferences.edit().putBoolean("IS_FIRST_TIME", false).apply()
        }

        buttonSetVisibility()

        mPlayer = MediaPlayer.create(activity, R.raw.alarm)
        view.startAlarm.setOnClickListener {
            if (flag == 0) {
                flag = 1
                startAlarm.visibility = View.INVISIBLE
                stopAlarm.visibility = View.VISIBLE
                mPlayer?.start()
                mPlayer?.isLooping=true
            }
        }

        view.startAlarmHindi.setOnClickListener {
            if (flag == 0) {
                flag = 1
                startAlarmHindi.visibility = View.INVISIBLE
                stopAlarmHindi.visibility = View.VISIBLE
                mPlayer?.start()
                mPlayer?.isLooping=true
            }
        }

        view.stopAlarm.setOnClickListener {
            if (flag==1)
            {
                flag=0
                stopAlarm.visibility = View.INVISIBLE
                startAlarm.visibility = View.VISIBLE
                mPlayer?.pause()

            }
        }

        view.stopAlarmHindi.setOnClickListener {
            if (flag==1)
            {
                flag=0
                stopAlarmHindi.visibility = View.INVISIBLE
                startAlarmHindi.visibility = View.VISIBLE
                mPlayer?.pause()

            }
        }

        view.startFlash.setOnClickListener()
        {
            if(flashflag==0)
            {
                flashflag=1
                startFlash.visibility=View.INVISIBLE
                stopFlash.visibility=View.VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    openFlashLight()
                }
            }
        }

        view.startFlashHindi.setOnClickListener()
        {
            if(flashflag==0)
            {
                flashflag=1
                startFlashHindi.visibility=View.INVISIBLE
                stopFlashHindi.visibility=View.VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    openFlashLight()
                }
            }
        }

        view.stopFlash.setOnClickListener()
        {
            if (flashflag==1)
            {
                flashflag=0
                stopFlash.visibility = View.INVISIBLE
                startFlash.visibility = View.VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    openFlashLight()
                }
            }
        }

        view.stopFlashHindi.setOnClickListener()
        {
            if (flashflag==1)
            {
                flashflag=0
                stopFlashHindi.visibility = View.INVISIBLE
                startFlashHindi.visibility = View.VISIBLE
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    openFlashLight()
                }
            }
        }


        view.fireHelpline.setOnClickListener {
            fire=1
            askPermission()
        }

        view.fireHelplineHindi.setOnClickListener {
            fire=1
            askPermission()
        }

        view.womenHelpline.setOnClickListener {
            women = 1
            askPermission()
        }

        view.womenHelplineHindi.setOnClickListener {
            women = 1
            askPermission()
        }

        view.policeHelpline.setOnClickListener {
            police=1
            askPermission()
        }

        view.policeHelplineHindi.setOnClickListener {
            police=1
            askPermission()
        }

        view.ambulanceHelpline.setOnClickListener {
            ambulance=1
            askPermission()

        }

        view.ambulanceHelplineHindi.setOnClickListener {
            ambulance=1
            askPermission()

        }

        view.emergencybutton.setOnClickListener {
            askLocationPerm()
            Handler(Looper.getMainLooper()).postDelayed({ askCameraPerm() }, 1000)

        }

        view.emergencybuttonHindi.setOnClickListener {
            askLocationPerm()
            Handler(Looper.getMainLooper()).postDelayed({ askCameraPerm() }, 1000)

        }
        view.showlocation.setOnClickListener {
            val intent = Intent(this.context,userLocation::class.java)
            startActivity(intent)
        }

        view.showlocationHindi.setOnClickListener {
            val intent = Intent(this.context,userLocation::class.java)
            startActivity(intent)
        }

        // Return the fragment view/layout
        return view
    }
    /*private fun showNoFlashError() {
        Log.e("Alert", "No Flash")
    }*/

    /*@RequiresApi(Build.VERSION_CODES.M)
    private fun askFlashPerm(){
        if(checkSelfPermission(
                activity as MainActivity2,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(
                arrayOf(android.Manifest.permission.CAMERA),
                REQUEST_CAMERA
            )

        }
        else
        {
            openFlashLight()
        }
    }*/
    @RequiresApi(Build.VERSION_CODES.M)
    private fun openFlashLight() {
        val cameraManager = activity?.getSystemService(Context.CAMERA_SERVICE) as CameraManager?
        val cameraId = cameraManager?.cameraIdList?.get(0)
        if (!flashLightStatus) {
            try {
                if (cameraId != null) {
                    cameraManager.setTorchMode(cameraId, true)
                }
                flashLightStatus = true

            } catch (e: CameraAccessException) {
            }
        } else {
            try {
                if (cameraId != null) {
                    cameraManager.setTorchMode(cameraId, false)
                }
                flashLightStatus = false
            } catch (e: CameraAccessException) {
            }
        }

    }
    private fun askCameraPerm(){
        if(checkSelfPermission(
                activity as MainActivity2,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(
                arrayOf(android.Manifest.permission.CAMERA),
                REQUEST_CAMERA
            )

        }
        else
        {
            askWriteToExternalStoragePerm()
        }
    }

    private fun askWriteToExternalStoragePerm() {
        if(checkSelfPermission(
                activity as MainActivity2,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_WRITE_EXTERNAL
            )

        }
        else
        {
            takePicture()
        }

    }

    private fun takePicture() {
        val intent = Intent(activity, captureImage::class.java)
        startActivity(intent)

    }

    private fun askSendSMSPerm() {
        if(checkSelfPermission
                (activity as MainActivity2, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(arrayOf(android.Manifest.permission.SEND_SMS), REQUEST_SEND_SMS)

        }
        else
        {
            sendSMSToContacts()
        }
    }

    private fun askLocationPerm() {
        if(checkSelfPermission(
                activity as MainActivity2,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION
            )

        }
        else
        {
            try {
                getLocationCurrent()
            }
            catch (e: Exception)
            {
                print("ExceptionCaught")
            }

        }
    }


    private fun getLocationCurrent() {
        val request = LocationRequest()
        request.interval = 10000
        request.fastestInterval = 5000
        request.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        val permission = checkSelfPermission(
            activity as MainActivity2,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        )
        if (permission == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(request, object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    val location: Location? = locationResult.lastLocation
                    if (location != null) {
                        val loc = locationDetails(
                            location.latitude.toString(),
                            location.longitude.toString()

                        )
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
        if (checkSelfPermission(activity as MainActivity2, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(arrayOf(android.Manifest.permission.CALL_PHONE), REQUEST_PHONE_CALL)
        }
        else
        {
            makePhoneCall()
        }

    }

    private fun makePhoneCall() {
        var phoneNumber:Long = 0
        when {
            fire ==1 -> {
                phoneNumber = 9560983181
            }
            police ==1 -> {
                phoneNumber = 9560983181
            }
            women ==1 -> {
                phoneNumber = 9560983181
            }
            ambulance ==1 -> {
                phoneNumber = 9560983181
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
        if (requestCode == REQUEST_CAMERA)
        {
            askWriteToExternalStoragePerm()

        }
        if (requestCode == REQUEST_WRITE_EXTERNAL)
        {
            takePicture()

        }
    }

    fun buttonSetVisibility() {
        var loggedInUser: User
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener { document ->
                loggedInUser = document.toObject(User::class.java)!!
                if (loggedInUser.lang == "en") {

                    womenHelpline.isVisible = true
                    policeHelpline.isVisible = true
                    ambulanceHelpline.isVisible = true
                    fireHelpline.isVisible = true
                    emergencybutton.isVisible = true
                    startAlarm.isVisible = true
                    stopAlarm.isVisible = false
                    startFlash.isVisible = true
                    stopFlash.isVisible = false
                    showlocation.isVisible = true
                    womenHelpline.isClickable = true
                    policeHelpline.isClickable = true
                    ambulanceHelpline.isClickable = true
                    fireHelpline.isClickable = true
                    emergencybutton.isClickable = true
                    startAlarm.isClickable = true
                    stopAlarm.isClickable = true
                    startFlash.isClickable = true
                    stopFlash.isClickable = true
                    showlocation.isClickable = true
                    womenHelplineHindi.isVisible = false
                    policeHelplineHindi.isVisible = false
                    ambulanceHelplineHindi.isVisible = false
                    fireHelplineHindi.isVisible = false
                    emergencybuttonHindi.isVisible = false
                    startAlarmHindi.isVisible = false
                    stopAlarmHindi.isVisible = false
                    startFlashHindi.isVisible = false
                    stopFlashHindi.isVisible = false
                    showlocationHindi.isVisible = false
                    womenHelplineHindi.isClickable = false
                    policeHelplineHindi.isClickable = false
                    ambulanceHelplineHindi.isClickable = false
                    fireHelplineHindi.isClickable = false
                    emergencybuttonHindi.isClickable = false
                    startAlarmHindi.isClickable = false
                    stopAlarmHindi.isClickable = false
                    startFlashHindi.isClickable = false
                    stopFlashHindi.isClickable = false
                    showlocationHindi.isClickable = false



                }
                if (loggedInUser.lang == "hi") {

                    womenHelplineHindi.isVisible = true
                    policeHelplineHindi.isVisible = true
                    ambulanceHelplineHindi.isVisible = true
                    fireHelplineHindi.isVisible = true
                    emergencybuttonHindi.isVisible = true
                    startAlarmHindi.isVisible = true
                    stopAlarmHindi.isVisible = false
                    startFlashHindi.isVisible = true
                    stopFlashHindi.isVisible = false
                    showlocationHindi.isVisible = true
                    womenHelplineHindi.isClickable = true
                    policeHelplineHindi.isClickable = true
                    ambulanceHelplineHindi.isClickable = true
                    fireHelplineHindi.isClickable = true
                    emergencybuttonHindi.isClickable = true
                    startAlarmHindi.isClickable = true
                    stopAlarmHindi.isClickable = true
                    startFlashHindi.isClickable = true
                    stopFlashHindi.isClickable = true
                    showlocationHindi.isClickable = true
                    showlocation.isClickable = true
                    womenHelpline.isVisible = false
                    policeHelpline.isVisible = false
                    ambulanceHelpline.isVisible = false
                    fireHelpline.isVisible = false
                    emergencybutton.isVisible = false
                    startAlarm.isVisible = false
                    stopAlarm.isVisible = false
                    startFlash.isVisible = false
                    stopFlash.isVisible = false
                    showlocation.isVisible = false
                    womenHelpline.isClickable = false
                    policeHelpline.isClickable = false
                    ambulanceHelpline.isClickable = false
                    fireHelpline.isClickable = false
                    emergencybutton.isClickable = false
                    startAlarm.isClickable = false
                    stopAlarm.isClickable = false
                    startFlash.isClickable = false
                    stopFlash.isClickable = false
                    showlocation.isClickable = false

                }
            }

    }

    fun getCurrentUserId():String
    {
        val uid = Firebase.auth.currentUser!!.uid
        return uid
    }




}
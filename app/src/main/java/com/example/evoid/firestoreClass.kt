package com.example.evoid

import android.app.ActionBar
import android.app.Activity
import android.location.Location
import android.media.session.MediaSessionManager
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.drawable.toDrawable
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class firestoreClass {

    private val mFireStore = FirebaseFirestore.getInstance()



    fun getLoggedInUser(activity:Activity) {
        val navigationView : NavigationView = activity.findViewById(R.id.nav_view)
        val headerView : View = navigationView.getHeaderView(0)
        var image = headerView.findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.profileIconNav)
        val name = headerView.findViewById<TextView>(R.id.userDetails)

        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener { document ->
                val loggedInUser = document.toObject(com.example.evoid.User::class.java)!!
                updateNavBar(activity, loggedInUser, name, image)
            }
    }


    fun updateNavBar(activity: Activity, user:com.example.evoid.User, name: TextView , image:de.hdodenhof.circleimageview.CircleImageView)
    {

        Glide
            .with(activity)
            .load(user.image)
            .fitCenter()
            .placeholder(R.drawable.cybercrime)
            .into(image);
        name.text = user.firstName + " " + user.lastName


    }

    fun registerUser(activity:RegisterUser, userInfo:com.example.evoid.User)
    {
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                Toast.makeText(activity, "registered", Toast.LENGTH_SHORT).show()
            }
    }



    fun getCurrentUserId():String
    {
        val uid = Firebase.auth.currentUser!!.uid
        return uid


    }

    fun addContact(contactInfo:ContactsDetails, phoneId:String)
    {
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .collection(constants.ContactsDetails).document(phoneId)
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





}
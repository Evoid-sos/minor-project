package com.example.evoid

import android.media.session.MediaSessionManager
import android.widget.Toast
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


    fun registerUser(activity:register, userInfo:com.example.evoid.User)
    {
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                Toast.makeText(activity, "registered", Toast.LENGTH_SHORT).show()
            }
    }

    fun displayRegisterUser()
    {
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get()
            .addOnSuccessListener {document->
                val loggedInUser = document.toObject(User::class.java)!!
                displayRegisteredDetails(loggedInUser)

        }
    }

    private fun displayRegisteredDetails(loggedInUser: User) {



    }

    fun getCurrentUserId():String
    {
        val uid = Firebase.auth.currentUser!!.uid
        return uid


    }

}
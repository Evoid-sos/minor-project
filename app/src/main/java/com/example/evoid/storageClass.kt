package com.example.evoid

import android.app.Activity
import android.net.Uri
import android.os.Handler
import android.webkit.MimeTypeMap
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class storageClass {
    var uriBack = ""
    var uriFront = ""
    fun insert(img:Uri, activity: Activity){
        val sref: StorageReference = FirebaseStorage
            .getInstance()
            .reference.child(firestoreClass().getCurrentUserId())
            .child("User_Image" + System.currentTimeMillis() + "." + getFileExtension(activity, img))

        sref
            .putFile(img)
            .addOnSuccessListener {snap->
                snap.metadata!!.reference!!.downloadUrl.addOnSuccessListener {uri->
                    firestoreClass().updateFirestoreImage(uri.toString())
                }

            }

    }

    fun insertEmergency(imgBack: Uri, imgFront:Uri, activity: Activity){

        val sref: StorageReference = FirebaseStorage
            .getInstance()
            .reference.child(firestoreClass().getCurrentUserId())
            .child("Emergency_Image_Back" + System.currentTimeMillis() + "." + "jpg")

        val sref1: StorageReference = FirebaseStorage
            .getInstance()
            .reference.child(firestoreClass().getCurrentUserId())
            .child("Emergency_Image_Front" + System.currentTimeMillis() + "." + "jpg")

        sref
            .putFile(imgBack)
            .addOnSuccessListener { snap ->
                snap.metadata!!.reference!!.downloadUrl.addOnSuccessListener { uri ->
                    uriBack = uri.toString()}
                    sref1
                        .putFile(imgFront)
                        .addOnSuccessListener { snap ->
                            snap.metadata!!.reference!!.downloadUrl.addOnSuccessListener { uri ->
                                uriFront = uri.toString()
                                val handler = Handler()
                                handler.postDelayed(
                                    {
                                        //Toast.makeText(activity, "$uriBack", Toast.LENGTH_SHORT).show()
                                        val picture = pictures(uriBack, uriFront)
                                        firestoreClass().saveEmergencyImage(picture,imgBack,imgFront,activity)
                                    }, 500)

                            }

                }


            }

    }



    fun getFileExtension(activity:Activity,uri: Uri?): String? {
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))

    }
}
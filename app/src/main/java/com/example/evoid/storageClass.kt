package com.example.evoid

import android.app.Activity
import android.net.Uri
import android.webkit.MimeTypeMap
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class storageClass {

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

    fun getFileExtension(activity:Activity,uri: Uri?): String? {
        return MimeTypeMap.getSingleton()
            .getExtensionFromMimeType(activity.contentResolver.getType(uri!!))

    }
}
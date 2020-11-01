package com.example.evoid

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.view.View
import android.view.WindowManager
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_my_profile.*
import kotlinx.android.synthetic.main.activity_register_user.*

class MyProfile : AppCompatActivity() {
    val REQUEST_EXTERNAL_STORAGE = 1
    val PICK_IMAGE_REQUEST = 2
    private var selectedImage: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_my_profile)
        cardViewMyProfile.setBackgroundResource(R.drawable.cardcorners)
        firestoreClass().loadMyProfile(this, changeImageMyProfile, emailIdMyProfile, nameMyProfile, mobileMyProfile)

        changeImageMyProfile.setOnClickListener {

            askStoragePerm()
        }

        updateMyProfile.setOnClickListener {
            if (selectedImage != null)
            {
                storageClass().insert(selectedImage!!, this)

            }

            firestoreClass().updateMyProfile(nameMyProfile.text.toString(), mobileMyProfile.text.toString())
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()

        }
    }

    private fun askStoragePerm() {
        if(ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),REQUEST_EXTERNAL_STORAGE)

        }
        else
        {
            pickImage()
        }
    }

    private fun pickImage() {
        var galleryIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_EXTERNAL_STORAGE )
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults.isNotEmpty())
            {
                pickImage()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == PICK_IMAGE_REQUEST && data!!.data != null)
        {
            selectedImage = data.data
            Glide
                .with(this)
                .load(selectedImage)
                .centerInside()
                .placeholder(R.drawable.placeholder3)
                .into(changeImageMyProfile);
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
        finish()
    }



}
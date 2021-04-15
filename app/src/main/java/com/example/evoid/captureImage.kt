package com.example.evoid

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_capture_image.*
import java.io.File

class captureImage : AppCompatActivity() {

    var camera:Camera?=null
    var preview:Preview?=null
    var imageCapture:ImageCapture?=null
    lateinit var path1:Uri
    lateinit var path2:Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_capture_image)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(
            {
                val cameraProvider = cameraProviderFuture.get()
                val flashMode = ImageCapture.FLASH_MODE_AUTO
                preview = Preview.Builder().build()
                preview?.setSurfaceProvider(previewPhoto.surfaceProvider)
                imageCapture = ImageCapture.Builder().setFlashMode(flashMode).build()
                val cameraSelector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()

                cameraProvider.unbindAll()
                camera =
                    cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)

            }, ContextCompat.getMainExecutor(this)
        )
        capture.setOnClickListener {
            takePhoto()
        }



    }

    private fun showImageAgain()
    {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(
            {
                val cameraProvider = cameraProviderFuture.get()
                val flashMode = ImageCapture.FLASH_MODE_AUTO
                preview = Preview.Builder().build()
                preview?.setSurfaceProvider(previewPhoto.surfaceProvider)
                imageCapture = ImageCapture.Builder().setFlashMode(flashMode).build()
                val cameraSelector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                    .build()

                cameraProvider.unbindAll()
                camera =
                    cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)

            }, ContextCompat.getMainExecutor(this)
        )
        capture.setOnClickListener {
            takePhotoAgain()
        }
    }

    private fun takePhotoAgain()
    {
        val photoFile = File(externalMediaDirs.firstOrNull(),"Evoid2-${System.currentTimeMillis()}.jpg")
        val output = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture?.takePicture(
            output,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    path2 = outputFileResults.savedUri ?: FileProvider.getUriForFile(applicationContext,BuildConfig.APPLICATION_ID + ".provider", photoFile)
                    Toast.makeText(applicationContext, "uploaded", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
                }

            }


        )
        Handler(Looper.getMainLooper()).postDelayed({
            storageClass().insertEmergency(
                Uri.parse(path1.toString()),
                Uri.parse(path2.toString()),
                this@captureImage)
        }, 1000)
        Handler(Looper.getMainLooper()).postDelayed({
            finish()
        }, 5500)

    }


    private fun takePhoto() {
        val photoFile = File(externalMediaDirs.firstOrNull(),"Evoid-${System.currentTimeMillis()}.jpg")
        val output = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        imageCapture?.takePicture(
            output,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    path1 = outputFileResults.savedUri ?: FileProvider.getUriForFile(applicationContext,BuildConfig.APPLICATION_ID + ".provider", photoFile)
                    Toast.makeText(applicationContext, "uploaded", Toast.LENGTH_SHORT)
                        .show()
                    showImageAgain()
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(applicationContext, "error", Toast.LENGTH_SHORT).show()
                }

            }


        )
    }
}



package com.example.evoid

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        setContentView(R.layout.activity_register)
    }
}
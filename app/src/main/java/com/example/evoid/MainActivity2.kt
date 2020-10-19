package com.example.evoid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.whenCreated
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG

class MainActivity2 : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater;
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

         super.onOptionsItemSelected(item)
        when(item.itemId)
        {
            R.id.guidelines -> Toast.makeText(applicationContext, "Guidelines", Toast.LENGTH_SHORT).show()
            R.id.covid -> startActivity(Intent(this,covidHelplines::class.java))
            R.id.cybercrime -> Toast.makeText(applicationContext, "CyberCrimes", Toast.LENGTH_SHORT).show()
            R.id.help -> Toast.makeText(applicationContext, "Help", Toast.LENGTH_SHORT).show()
            R.id.logout -> Toast.makeText(applicationContext, "Logout", Toast.LENGTH_SHORT).show()
            else -> Toast.makeText(applicationContext, "Could not complete the request", Toast.LENGTH_SHORT).show()
        }
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val panicButton = findViewById(R.id.panicButton) as ImageView
        panicButton.setOnClickListener()
        {
            Toast.makeText(applicationContext,"PANIC",Toast.LENGTH_LONG).show()
        }
    }
}
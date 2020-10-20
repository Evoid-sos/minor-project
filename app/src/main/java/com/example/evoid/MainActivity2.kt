package com.example.evoid

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity2 : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater;
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu)
    }


    var auth = Firebase.auth

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

         super.onOptionsItemSelected(item)
        when(item.itemId)
        {
            R.id.guidelines -> startActivity(Intent(this, guidelines::class.java))
            R.id.covid -> startActivity(Intent(this, covidHelplines::class.java))
            R.id.cybercrime -> startActivity(Intent(this, cybersecurity::class.java))
            R.id.help -> startActivity(Intent(this, faqs::class.java))
            R.id.logout -> signOut()
            else -> Toast.makeText(
                applicationContext,
                "Could not complete the request",
                Toast.LENGTH_SHORT
            ).show()
        }
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val panicButton = findViewById(R.id.panicButton) as ImageView
        panicButton.setOnClickListener()
        {
            Toast.makeText(applicationContext, "PANIC", Toast.LENGTH_LONG).show()
        }
    }

    private fun signOut()
    {

        auth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("finish", true) // if you are checking for this in your other Activities

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
package com.example.evoid

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

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
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab().setText("EMERGENCY"))
        tabLayout.addTab(tabLayout.newTab().setText("ADD CONTACTS"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = MyAdapter(this, supportFragmentManager,
            tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

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
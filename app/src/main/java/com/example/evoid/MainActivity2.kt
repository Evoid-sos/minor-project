package com.example.evoid

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity2 : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener{
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    var auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        drawerLayout = findViewById(R.id.drawer)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        toggle.syncState()
        firestoreClass().getLoggedInUser(this)
        navView.setNavigationItemSelectedListener(this)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        setSupportActionBar(toolbar)
        tabLayout.addTab(tabLayout.newTab().setText("EMERGENCY"))
        tabLayout.addTab(tabLayout.newTab().setText("EMERGENCY CONTACTS"))
        tabLayout.addTab(tabLayout.newTab().setText("MENTAL HEALTH AWARENESS"))
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

        val navigationView : NavigationView = this.findViewById(R.id.nav_view)
        val headerView : View = navigationView.getHeaderView(0)
        var image = headerView.findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.profileIconNav)
        image.setOnClickListener{
            val intent = Intent(this, MyProfile::class.java)
            startActivity(intent)
        }

    }


    private fun signOut()
    {

        auth.signOut()
        val intent = Intent(this, start_page::class.java)
        intent.putExtra("finish", true) // if you are checking for this in your other Activities

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.nearhelp -> startActivity(Intent(this, nearbyHelp::class.java))
            R.id.guidelines -> startActivity(Intent(this, newGuidelines::class.java))
            R.id.covid -> startActivity(Intent(this, covidHelplines::class.java))
            R.id.cybercrime -> startActivity(Intent(this, cybersecurity::class.java))
            R.id.medicalInfo -> startActivity(Intent(this, medicalInformation::class.java))
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
    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
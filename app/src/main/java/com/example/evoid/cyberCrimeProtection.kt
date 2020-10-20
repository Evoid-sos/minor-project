package com.example.evoid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import kotlinx.android.synthetic.main.activity_cyber_crime_protection.*

class cyberCrimeProtection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cyber_crime_protection)
        reportLink.movementMethod=LinkMovementMethod.getInstance()
        cybercrime.setOnClickListener()
        {
            showGuidelines.text="1. Make the passwords more complicated by combining letters, numbers, special characters (minimum 10 characters in total) and change them on a regular basis.\n\n" +
                    "2. Use anti-virus/malware software\n\n" +
                    "3. Prevent spyware from infiltrating your computer by installing and updating anti-spyware software.\n\n" +
                    "4. Make sure your social networking profiles (e.g. Facebook, Twitter, YouTube, MSN, etc.) are set to private.\n\n" +
                    "5. Be careful what information you post online. Once it is on the Internet, it is there forever\n\n" +
                    "6. Turn on automatic updates to prevent potential attacks on older software.\n\n" +
                    "7. Public Wi-Fi, a.k.a. “Hot Spots”, are also vulnerable. Avoid conducting financial or corporate transactions on these networks.\n\n" +
                    "8. Always think before you click on a link or file of unknown origin.\n\n\n\n\n\n\n"
        }
    }
}
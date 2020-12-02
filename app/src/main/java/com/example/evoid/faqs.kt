package com.example.evoid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import kotlinx.android.synthetic.main.activity_faqs.*

class faqs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faqs)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = Html.fromHtml("<font color='#fffffff'> <small>FREQUENTLY ASKED QUESTIONS </font></small>")
        val one="<b><i>Q: How Does This App Work?</i></b> <br> A:This App Provides You With Features To Contact Emergency Service With Just A Tap. If Panic Button Is Pressed, Your Emergency Contacts Will Receive An Alert With Your Location. Please Note That Your Location Is Being Stored To Our Database While This App Is Active To Provide Accurate Results. You Can Also View Guidelines To Protect Yourself In Case Of Emergency.<br><br>" +
                "<b><i>Q: Is This App Free To Use?</i></b> <br> A:Yes, This App Is Free To Use. However, SMS Charges May Apply While Sending SMS To Your Emergency Contacts<br><br>" +
                "<b><i>Q: Do I Need To Login Every time I Use The App?</i></b> <br> A: No, This App Supports One Time Login. You Stay Logged In Until You Press Logout.<br><br>" +
                "<b><i>Q: Can I Use This App In IPhone?</i></b> <br> A: Currently This App Only Supports Android Mobile Phones and Tablets.<br><br>"+
                "<b><i>Q: How Can I Add or Edit My Emergency Contacts? </i></b> <br> A: Contacts Can Be Added By Choosing The Add Contacts Option. The Maximum Number Of Contacts That Can Be Added Are 5. You Can Delete A Contact By Tapping The Icon In Front Of Contact Name"
        quesOne.text=Html.fromHtml(one)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
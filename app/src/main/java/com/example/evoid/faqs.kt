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
        title="Frequently Asked Questions"
        val one="<b><i>Q: Is This App Free To Use?</i></b> <br> A:Yes, This App Is Free To Use.<br><br>" +
                "<b><i>Q: Do I Need To Login Every time I Use The App?</i></b> <br> A: No, This App Supports One Time Login. You Stay logged In Until You Press LogOut.<br><br>" +
                "<b><i>Q: Can I Use This App In IPhone?</i></b> <br> A: No, This App Only Supports Android Mobile Phones and Tablets.<br><br>"
        quesOne.text=Html.fromHtml(one)

    }
}
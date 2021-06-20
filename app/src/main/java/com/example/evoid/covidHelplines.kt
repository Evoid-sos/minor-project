package com.example.evoid

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_covid_helplines.*
import kotlinx.android.synthetic.main.activity_faqs.*

class covidHelplines : AppCompatActivity() {
    private val mFireStore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_helplines)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        getActionBarLang(supportActionBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        buttonSetVisibilityHere()

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun getActionBarLang(supportActionBar: ActionBar?) {
        var loggedInUser: User
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener { document ->
                loggedInUser = document.toObject(User::class.java)!!
                if (loggedInUser.lang == "en") {

                    supportActionBar?.title = Html.fromHtml("<font color='#fffffff'>COVID-19 HELPLINES </font>")

                }
                if (loggedInUser.lang == "hi") {

                    supportActionBar?.title = Html.fromHtml("<font color='#fffffff'>कोविड-19 हेल्पलाइन्स </font>")

                }
            }

    }

    fun getCurrentUserId():String
    {
        val uid = Firebase.auth.currentUser!!.uid
        return uid
    }

    fun buttonSetVisibilityHere() {
        var loggedInUser: User
        mFireStore.collection(constants.USERS)
            .document(getCurrentUserId())
            .get().addOnSuccessListener { document ->
                loggedInUser = document.toObject(User::class.java)!!
                if (loggedInUser.lang == "en") {

                    textView2.setText(R.string.choose_a_state_to_display_helpline_number)

                    val states = resources.getStringArray(R.array.States)
                    val spinner = R.id.spinner1
                    if (spinner != null) {
                        val adapter = ArrayAdapter(this,
                            android.R.layout.simple_spinner_item, states)
                        spinner1.adapter = adapter
                        val languages = resources.getStringArray(R.array.States)
                        // access the spinner
                        val spinner = findViewById<Spinner>(R.id.spinner1)
                        if (spinner != null) {
                            val adapter = ArrayAdapter(
                                this,
                                android.R.layout.simple_spinner_item, languages
                            )
                            spinner.adapter = adapter

                            spinner.onItemSelectedListener = object :
                                AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>,
                                    view: View, position: Int, id: Long
                                ) {
                                    when(spinner.selectedItemId)
                                    {
                                        spinner.getItemIdAtPosition(0)-> covidDisp.text = "03192-232102"
                                        spinner.getItemIdAtPosition(1)-> covidDisp.text = "0866-2410978"
                                        spinner.getItemIdAtPosition(2)-> covidDisp.text = "9436055743"
                                        spinner.getItemIdAtPosition(3)-> covidDisp.text = "6913347770"
                                        spinner.getItemIdAtPosition(4)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(5)-> covidDisp.text = "9779558282"
                                        spinner.getItemIdAtPosition(6)-> covidDisp.text = "077122-35091"
                                        spinner.getItemIdAtPosition(7)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(8)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(9)-> covidDisp.text = "011-22307145"
                                        spinner.getItemIdAtPosition(10)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(11)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(12)-> covidDisp.text = "8558893911"
                                        spinner.getItemIdAtPosition(13)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(14)-> covidDisp.text = "01912520982"
                                        spinner.getItemIdAtPosition(15)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(16)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(17)-> covidDisp.text = "01942440283"
                                        spinner.getItemIdAtPosition(18)-> covidDisp.text = "0471-2552056"
                                        spinner.getItemIdAtPosition(19)-> covidDisp.text = "01982256462"
                                        spinner.getItemIdAtPosition(20)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(21)-> covidDisp.text = "0755-2527177"
                                        spinner.getItemIdAtPosition(22)-> covidDisp.text = "020-26127394"
                                        spinner.getItemIdAtPosition(23)-> covidDisp.text = "03852411668"
                                        spinner.getItemIdAtPosition(24)-> covidDisp.text = "108"
                                        spinner.getItemIdAtPosition(25)-> covidDisp.text = "102"
                                        spinner.getItemIdAtPosition(26)-> covidDisp.text = "7005539653"
                                        spinner.getItemIdAtPosition(27)-> covidDisp.text = "9439994859"
                                        spinner.getItemIdAtPosition(28)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(29)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(30)-> covidDisp.text = "0141-2225624"
                                        spinner.getItemIdAtPosition(31)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(32)-> covidDisp.text = "044-29510500"
                                        spinner.getItemIdAtPosition(33)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(34)-> covidDisp.text = "0381-2315879"
                                        spinner.getItemIdAtPosition(35)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(36)-> covidDisp.text = "18001805145"
                                        spinner.getItemIdAtPosition(37)-> covidDisp.text = "03323412600"
                                    }


                                    /*Toast.makeText(
                                        applicationContext,
                                        getString(R.string.selected_item) + " " +
                                                "" + languages[position], Toast.LENGTH_SHORT
                                    ).show()*/
                                }

                                override fun onNothingSelected(parent: AdapterView<*>) {
                                    // write code to perform some action
                                }
                            }

                        }
                    }


                }
                if (loggedInUser.lang == "hi") {

                    textView2.setText(R.string.choose_a_state_to_display_helpline_number_hindi)

                    val states = resources.getStringArray(R.array.StatesHindi)
                    val spinner = R.id.spinner1
                    if (spinner != null) {
                        val adapter = ArrayAdapter(this,
                            android.R.layout.simple_spinner_item, states)
                        spinner1.adapter = adapter
                        val languages = resources.getStringArray(R.array.StatesHindi)
                        // access the spinner
                        val spinner = findViewById<Spinner>(R.id.spinner1)
                        if (spinner != null) {
                            val adapter = ArrayAdapter(
                                this,
                                android.R.layout.simple_spinner_item, languages
                            )
                            spinner.adapter = adapter

                            spinner.onItemSelectedListener = object :
                                AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>,
                                    view: View, position: Int, id: Long
                                ) {
                                    when(spinner.selectedItemId)
                                    {
                                        spinner.getItemIdAtPosition(0)-> covidDisp.text = "03192-232102"
                                        spinner.getItemIdAtPosition(1)-> covidDisp.text = "0866-2410978"
                                        spinner.getItemIdAtPosition(2)-> covidDisp.text = "9436055743"
                                        spinner.getItemIdAtPosition(3)-> covidDisp.text = "6913347770"
                                        spinner.getItemIdAtPosition(4)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(5)-> covidDisp.text = "9779558282"
                                        spinner.getItemIdAtPosition(6)-> covidDisp.text = "077122-35091"
                                        spinner.getItemIdAtPosition(7)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(8)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(9)-> covidDisp.text = "011-22307145"
                                        spinner.getItemIdAtPosition(10)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(11)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(12)-> covidDisp.text = "8558893911"
                                        spinner.getItemIdAtPosition(13)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(14)-> covidDisp.text = "01912520982"
                                        spinner.getItemIdAtPosition(15)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(16)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(17)-> covidDisp.text = "01942440283"
                                        spinner.getItemIdAtPosition(18)-> covidDisp.text = "0471-2552056"
                                        spinner.getItemIdAtPosition(19)-> covidDisp.text = "01982256462"
                                        spinner.getItemIdAtPosition(20)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(21)-> covidDisp.text = "0755-2527177"
                                        spinner.getItemIdAtPosition(22)-> covidDisp.text = "020-26127394"
                                        spinner.getItemIdAtPosition(23)-> covidDisp.text = "03852411668"
                                        spinner.getItemIdAtPosition(24)-> covidDisp.text = "108"
                                        spinner.getItemIdAtPosition(25)-> covidDisp.text = "102"
                                        spinner.getItemIdAtPosition(26)-> covidDisp.text = "7005539653"
                                        spinner.getItemIdAtPosition(27)-> covidDisp.text = "9439994859"
                                        spinner.getItemIdAtPosition(28)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(29)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(30)-> covidDisp.text = "0141-2225624"
                                        spinner.getItemIdAtPosition(31)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(32)-> covidDisp.text = "044-29510500"
                                        spinner.getItemIdAtPosition(33)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(34)-> covidDisp.text = "0381-2315879"
                                        spinner.getItemIdAtPosition(35)-> covidDisp.text = "104"
                                        spinner.getItemIdAtPosition(36)-> covidDisp.text = "18001805145"
                                        spinner.getItemIdAtPosition(37)-> covidDisp.text = "03323412600"
                                    }


                                    /*Toast.makeText(
                                        applicationContext,
                                        getString(R.string.selected_item) + " " +
                                                "" + languages[position], Toast.LENGTH_SHORT
                                    ).show()*/
                                }

                                override fun onNothingSelected(parent: AdapterView<*>) {
                                    // write code to perform some action
                                }
                            }

                        }
                    }
                }
            }

    }
}
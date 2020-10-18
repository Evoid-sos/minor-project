package com.example.evoid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_covid_helplines.*

class covidHelplines : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covid_helplines)
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
                        Toast.makeText(
                            applicationContext,
                            getString(R.string.selected_item) + " " +
                                    "" + languages[position], Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                        // write code to perform some action
                    }
                }
            }}}}
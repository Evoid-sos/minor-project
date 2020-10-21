package com.example.evoid

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_emergency.view.*


class emergency : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_emergency, container, false)

        view.fireHelpline.setOnClickListener { view ->
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:9560983181")
            startActivity(intent)
        }
        view.womenHelpline.setOnClickListener { view ->
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:9560983181")
            startActivity(intent)
        }
        view.policeHelpline.setOnClickListener { view ->
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:9560983181")
            startActivity(intent)
        }
        view.ambulanceHelpline.setOnClickListener { view ->
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:9560983181")
            startActivity(intent)
        }
        // Return the fragment view/layout
        return view
    }
}
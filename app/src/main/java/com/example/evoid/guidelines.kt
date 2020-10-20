package com.example.evoid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.ScrollView
import kotlinx.android.synthetic.main.activity_guidelines.*

class guidelines : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guidelines)
        title = "Guidelines"

        fire.setOnClickListener() {

            guideText.text ="1. Immediately pull the nearest fire alarm pull station as you exit the building\n\n" +
                    "2. When evacuating the building, be sure to feel doors for heat before opening them to be sure there is no fire danger on the other side.\n\n" +
                    "3. If there is smoke in the air, stay low to the ground, especially your head, to reduce inhalation exposure. Keep on hand on the wall to prevent disorientation and crawl to the nearest exit.\n\n" +
                    "4. Once away and clear from danger, call your report contact and inform them of the fire.\n\n" +
                    "5. Go to your refuge area and await further instructions from emergency personnel.\n\n\n\n\n\n\n"

        }
        medic.setOnClickListener()
        {
            guideText.text="1. Keep a first-aid kit handy at home and work\n\n" +
                    "2. Having an emergency app on your smartphone allows first responders access to critical medical information stored on your phone without knowing the passcode on the phone’s lock screen. Information can include prescriptions, allergies, medical conditions, blood type and emergency contacts.\n\n" +
                    "3. If you do have to go the ER, be prepared. Bring a list of current medications, including how long the medicine has been taken and how often.\n\n\n\n\n\n\n"
        }
        accident.setOnClickListener()
        {
            guideText.text="1. The first and foremost thing is your own safety, this will enable you to help others and get time to analyze the complete situation with a better viewpoint.\n\n" +
                    "2. Once you have reached safety, the next step is to make sure that everyone involved in the accident is safe and assess your own and their possible injuries.\n \n " +
                    "3. Once everyone involved has been diagnosed to be safe, it is imperative that you file a police report of the accident at the earliest and in as detailed a manner as you can manage.\n\n\n\n\n\n\n"
        }
        crime.setOnClickListener()
        {
            guideText.text= "1. Be AWARE of your surroundings at all times and trust your INSTINCTS.\n\n" +
                    "2. Walk with your head upright. Make eye contact. Thieves often target victims who are not paying attention to their surroundings or who are looking down.\n\n" +
                    "3. Plan your route, know where you are going before leaving.\n\n" +
                    "4. Stick to well-lighted, well-traveled streets. Avoid shortcuts through wooded areas, parking lots, or alleys.\n\n\n\n\n"
        }

    }
}